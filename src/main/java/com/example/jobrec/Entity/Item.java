package com.example.jobrec.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {
    private String id;
    private String title;
    private String location;
    private String companyName;
    private String via;
    private String description;
    private Set<String> keywords;
    private boolean favorite;



    public Item() {
    }


    public Item(String id, String title, String location, String companyName, String
            via, String description, Set<String> keywords, boolean favorite) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.companyName = companyName;
        this.via = via;
        this.description = description;
        this.keywords = keywords;
        this.favorite = favorite;
    }



    @JsonProperty("job_id")
    public String getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("company_name")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("via")
    public String getVia() {
        return via;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public boolean getFavorite() {
        return favorite;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", companyName='" + companyName + '\'' +
                ", via='" + via + '\'' +
                ", description='" + description + '\'' +
                ", keywords=" + keywords +
                ", favorite=" + favorite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return favorite == item.favorite && Objects.equals(id, item.id) && Objects.equals(title, item.title) && Objects.equals(location, item.location) && Objects.equals(companyName, item.companyName) && Objects.equals(via, item.via) && Objects.equals(description, item.description) && Objects.equals(keywords, item.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, location, companyName, via, description, keywords, favorite);
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
