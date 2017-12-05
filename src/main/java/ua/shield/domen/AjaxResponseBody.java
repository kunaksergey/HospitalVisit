package ua.shield.domen;

import ua.shield.dto.DoctorDto;

import java.util.List;

/**
 * Created by sa on 04.12.17.
 */
public class AjaxResponseBody {
    private String msg;
    private List<DoctorDto> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DoctorDto> getResult() {
        return result;
    }

    public void setResult(List<DoctorDto> result) {
        this.result = result;
    }
}
