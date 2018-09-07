package com.fgl.eai.iib.testing.scenarios

class InputBean {
    Map<String,String> sourceXpaths
    Map<String,String> targetXpaths
    List<String> targetStrings
    String jiraID
    String description


    @Override
    public String toString() {
        return "InputBean{" +
                "sourceXpaths=" + sourceXpaths +
                ", targetXpaths=" + targetXpaths +
                ", targetStrings=" + targetStrings +
                ", jiraID='" + jiraID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}