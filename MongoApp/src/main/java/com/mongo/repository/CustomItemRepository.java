package com.mongo.repository;

public interface CustomItemRepository {

    void updateItemQuantity(String itemName, float newQuantity);

}