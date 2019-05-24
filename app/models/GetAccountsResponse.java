package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetAccountsResponse {

    @JsonProperty("resources")
    public List<Account> accounts;

    /**
     * TODO: implement in the future
     *
     * "pagination": {
     *     "previous_uri": null,
     *     "next_uri": "/v2/accounts?after=MjM0MTUwMA%3D%3D&limit=10"
     *   }
     */
}
