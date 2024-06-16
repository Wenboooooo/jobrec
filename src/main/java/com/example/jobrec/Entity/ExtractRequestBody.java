package com.example.jobrec.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;


public class ExtractRequestBody {
    public String text;


    @JsonProperty("max_keywords")
    public int maxKeywords;


    public String language = "en";


    public String providers = "ibm";


    public String fallback_providers = "tenstorrent";


    @JsonProperty("response_as_dict")
    public boolean responseAsDict = true;


    public ExtractRequestBody(String text) {
        this.text = text;
    }
}

