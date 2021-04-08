package com.casper.sdk.service;

import com.casper.sdk.exceptions.ValueNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public enum MethodEnums {

    STATE_ROOT_HASH{
        @Override
        public String getValue(final String result) throws ValueNotFoundException {
            try{
                final JsonNode node = new ObjectMapper().readTree(result);
                return node.get("result").get("state_root_hash").textValue();
            } catch (Exception e){
                throw new ValueNotFoundException("state root hash not found");
            }
        }
    },
    STATE_GET_ITEM{
        @Override
        public String getValue(final String result) throws ValueNotFoundException {
            try{
                final JsonNode node = new ObjectMapper().readTree(result);
                return node.get("result").get("stored_value").get("Account").get("main_purse").textValue();
            } catch (Exception e){
                throw new ValueNotFoundException("main_purse not found");
            }
        }
    },
    STATE_GET_BALANCE{
        @Override
        public String getValue(final String result) throws ValueNotFoundException {
            try{
                final JsonNode node = new ObjectMapper().readTree(result);
                return node.get("result").get("balance_value").textValue();
            } catch (Exception e){
                throw new ValueNotFoundException("balance_value not found");
            }
        }
    };


    private static final List<String> MAP = new ArrayList<>();
    public abstract String getValue(final String result) throws JsonProcessingException, ValueNotFoundException;

    static {
        for (final MethodEnums value : values()) {
            MAP.add(value.name());
        }
    }


}
