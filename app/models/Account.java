package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("balance")
    public Double balance;
}
