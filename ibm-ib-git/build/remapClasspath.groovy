#!/usr/bin/env groovy
/********************************************************************
 * 2015-08-11 - Gabriel Chan - gabriel.chan@middleware360.com
 * v1.0 - initial version
 *
 * This file does processing of .classpath files and replaces hard-coded jar files to actual file paths
 */
public class RemapClasspath {
    public static void main(String[] args) {
        if (args.length == 2) {
            ClasspathConverter converter = new ClasspathConverter();
            converter.libraryDir = args[0]
            converter.getCommonJarList().each { k, v ->
                println "found common jar file: $v"
            }
            def path = new File(args[1])
            println "processing dir: ${path.absolutePath}"
            if (path.exists()) {
                path.eachFileRecurse { file ->
                    if (file.isFile() && file.name.matches(".classpath")) {
                        println "processing file: $file.absolutePath"
                        converter.rewriteForFile(file)
                    }
                }
            }
        }
    }
}


public class ClasspathConverter {
    String libraryDir
    Map<String, String> commonJarList;

    public Map<String, String> getCommonJarList() {
        if (commonJarList == null) {
            detectCommonJars()
        }
        return commonJarList
    }

    private void detectCommonJars() {
        if (libraryDir != null) {
            File commonBase = new File(libraryDir)
            if (commonBase.exists()) {
                commonJarList = new HashMap<String, String>()
                commonBase.eachFileRecurse() { file ->
                    if (file.isFile() && file.name.endsWith(".jar")) {
                        println "found $file"
                        if (commonJarList.containsKey(file.name)) {
                            println "  jar name collision: old=" + commonJarList.get(file.name) + ", new=${file.absolutePath}"
                        }
                        commonJarList.put(file.name, file.absolutePath)
//                        commonJarList.add(file.absolutePath)
                    } else {
                        println "skipping $file"
                    }
                }
            }
        }
    }

    public void rewriteForFile(File xmlFile) {
        boolean dryRun = false;
        if (xmlFile?.exists()) {
            def classpathXml = new XmlParser().parse(xmlFile)
            boolean hasChanges = reMapXml(classpathXml)
            if (hasChanges) {
                try {
                    if (dryRun) {
                        StringWriter writer = new StringWriter();
                        new XmlNodePrinter(new PrintWriter(writer)).print(classpathXml)
                        println "response: " + writer.toString()
                        writer.close()
                    } else {
                        FileWriter writer = new FileWriter(xmlFile)
                        new XmlNodePrinter(new PrintWriter(writer)).print(classpathXml)
                        writer.close()
                        println "  wrote updated ${xmlFile.absolutePath}"
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace()
                }
            }
        }
    }

    public boolean reMapXml(Node xml) {
        boolean hasChanges = false
        if (xml.classpathentry.size() > 0) {
            xml.classpathentry.each { entry ->
                if (entry.@kind == "lib") {
                    String path = entry.@path
                    path = path.replaceFirst("^[a-zA-Z]:","")
                    String fileName = (new File(path).name)
                    getCommonJarList().findAll { k, v -> k.equals(fileName) }.each { k, v ->
                        println "  remapping: ${entry.@path} -> $v"
                        path = v.replaceAll("\\\\", '/')
                    }
                    if (!path.equals(entry.@path)) {
                        entry.@path=path
                        hasChanges = true
                    }
                }
            }
        }
        return hasChanges
    }
}