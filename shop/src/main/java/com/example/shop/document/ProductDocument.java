package com.example.shop.document;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(indexName = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setting(settingPath = "/elasticsearch/product-settings.json")
public class ProductDocument {
    @Id
    private String id;
    // "name":{
    // "type":"text",
    // "analyzer": "products_name_analyzer",
    // "fields": {
    // "auto_complete":{
    // "type":"search_as_you_type",
    // "analyzer": "nori"
    // }
    // }
    // } == ㄱ
    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "products_name_analyzer"), otherFields = {
            @InnerField(suffix = "auto_complete", type = FieldType.Search_As_You_Type, analyzer = "nori")
    })
    private String name;

    @Field(type = FieldType.Text, analyzer = "products_description_analyzer")
    private String description;

    @Field(type = FieldType.Integer)
    private int price;

    @Field(type = FieldType.Double)
    private double rating;

    @MultiField(mainField = @Field(type = FieldType.Text, analyzer = "products_category_analyzer"), otherFields = {
            @InnerField(suffix = "raw", type = FieldType.Keyword)
    })
    private String category;
}
