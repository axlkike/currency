package com.axlkike.currency.domain;

/**
 * Created by kikejf on 19/01/2016.
 */
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "T_CURRENCY_SEARCH")
public class CurrencySearch {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="user_id")
    @NotNull
    private String userId;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="search_date")
    @NotNull
    private DateTime searchDate;

    @Column(name="curr_from")
    @NotNull
    private String currFrom;

    @Column(name="curr_to")
    @NotNull
    private String currTo;

    @Column(name="historical")
    @NotNull
    private Boolean historical;

    @Column(name="exchange", precision=9, scale=6)
    @NotNull
    private BigDecimal exchange;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DateTime getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(DateTime searchDate) {
        this.searchDate = searchDate;
    }

    public String getCurrFrom() {
        return currFrom;
    }

    public void setCurrFrom(String currFrom) {
        this.currFrom = currFrom;
    }

    public String getCurrTo() {
        return currTo;
    }

    public void setCurrTo(String currTo) {
        this.currTo = currTo;
    }

    public Boolean getHistorical() {
        return historical;
    }

    public void setHistorical(Boolean historical) {
        this.historical = historical;
    }

    public BigDecimal getExchange() {
        return exchange;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
    }

    /**
     * empty constructor
     */
    public CurrencySearch() {   }

    /** Constructor */
    public CurrencySearch(String userId, DateTime searchDate, String currFrom,
                          String currTo, Boolean historical, BigDecimal exchange) {
        this.userId = userId;
        this.searchDate = searchDate;
        this.currFrom = currFrom;
        this.currTo = currTo;
        this.exchange = exchange;
        this.historical = historical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrencySearch currencySearch = (CurrencySearch) o;
        return Objects.equals(id, currencySearch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "CurrencySearch{" +
                "id=" + this.id +
                ", userId='" + this.userId + "'" +
                ", searchDate='" + this.searchDate + "'" +
                ", currFrom='" + this.currFrom + "'" +
                ", currTo='" + this.currTo + "'" +
                ", historical='" + this.historical + "'" +
                ", exchange='" + this.exchange + "'" +
                "}";
    }


}