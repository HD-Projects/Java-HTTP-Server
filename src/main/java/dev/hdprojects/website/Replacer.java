package dev.hdprojects.website;

public class Replacer {

    private String replaceString;
    private String contents;

    public Replacer(String replaceString, String contents) {
        this.contents = contents;
        this.replaceString = replaceString;
    }

    private String replace() {
        return this.replaceString.replaceAll(contents, replaceString);
    }

    @Override
    public String toString() {
        return contents;
    }

    /*
     *
     * Getters and Setters
     *
     */

    public String getReplaceString() {
        return replaceString;
    }

    public void setReplaceString(String replaceString) {
        this.replaceString = replaceString;
    }

    public String getContents() {
        return this.replace();
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
