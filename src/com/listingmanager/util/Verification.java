package com.listingmanager.util;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.mybusiness.v4.MyBusiness;
import com.google.api.services.mybusiness.v4.MyBusinessRequest;
import com.google.api.services.mybusiness.v4.model.ListAccountsResponse;

public class Verification extends MyBusiness {

    public Verification.List verfications() throws Exception {

        Verification.List list = new Verification.List();
        this.initialize(list);
        return list;
    }



    public Verification(HttpTransport transport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
        super(transport, jsonFactory, httpRequestInitializer);
    }

    public class List extends MyBusinessRequest<ListAccountsResponse> {
        private static final String REST_PATH = "v4/{+parent}/locations";

        protected List() {
            super(Verification.this, "GET", "v4/accounts", (Object)null, ListAccountsResponse.class);
        }
    }

}
