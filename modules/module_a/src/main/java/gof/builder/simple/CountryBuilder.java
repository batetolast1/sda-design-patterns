package gof.builder.simple;

import gof.builder.City;
import gof.builder.Country;

import java.util.LinkedList;

public class CountryBuilder {

    private Country country = new Country();

    public CountryBuilder() {
        this(null);
    }

    public CountryBuilder(Country c) {
        country = c;
    }

    public CountryBuilder name(String name) {
        country.setName(name);
        return this;
    }

    public CountryBuilder population(Long population) {
        country.setPopulation(population);
        return this;
    }

    public CountryBuilder capital(City capital) {
        country.setCapital(capital);
        city(capital);
        return this;
    }

    public CountryBuilder city(City city) {
        if (country.getCities() == null) {
            country.setCities(new LinkedList<>());
        }
        country.getCities().add(city);
        return this;
    }

    public Country build() {
        if (country.getName() == null) {
            throw new IllegalStateException("Pole `name` nie zostało ustawione");
        }
        if (country.getCapital() != null) {
            country.getCapital().setCountry(country);
        }
        if (country.getCities() != null) {
            for (City city : country.getCities()) {
                city.setCountry(country);
            }
        }
        return country;
    }
}
