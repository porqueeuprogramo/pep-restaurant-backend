package com.pep.restaurant.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "language")
    private String language;

    /**
     * Get Menu id.
     * @return menu id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set menu id.
     * @param id id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get Menu language.
     * @return menu language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set menu language.
     * @param language language.
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * Builder Menu for language.
     * @param language language to build.
     * @return menu with language.
     */
    public Menu language(final String language){
        this.language = language;
        return this;
    }
}