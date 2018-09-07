package com.marks.eai.iib.testing.scenarios

class InputBean {
    Map<String,String> sourceXpaths
    Map<String,String> targetXpaths
    String jiraID
    String description


    @Override
    public String toString() {
        return "InputBean{" +
                "sourceXpaths=" + sourceXpaths +
                ", targetXpaths=" + targetXpaths +
                ", jiraID='" + jiraID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}