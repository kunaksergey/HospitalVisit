package ua.shield.domen;

import ua.shield.entity.District;

public class SearchCriteria {
    private District district;
    private String searchStr;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
}
