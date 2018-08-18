package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    private Boolean available;


    private CarDTO()
    {
    }


    private CarDTO(Long id, Boolean available)
    {
        this.id = id;
        this.available = available;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public Boolean getAvailable()
    {
        return available;
    }


    public static class CarDTOBuilder
    {
        private Long id;
        private Boolean available;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setAvailable(Boolean available)
        {
            this.available = available;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, available);
        }

    }
}
