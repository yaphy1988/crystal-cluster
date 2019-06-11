package com.ai.mine.crystal.dto.resp;

import com.ai.mine.common.dto.BaseResponseDTO;

import java.util.List;

public class Select2RespDTO extends BaseResponseDTO {

    private List<Select2ItemDTO> results;

    private Select2PaginateDTO paginate;

    public List<Select2ItemDTO> getResults() {
        return results;
    }

    public void setResults(List<Select2ItemDTO> results) {
        this.results = results;
    }

    public void appendResults(Select2ItemDTO result) {
        if (!results.contains(result)) {
            results.add(result);
        }
    }

    public Select2PaginateDTO getPaginate() {
        return paginate;
    }

    public void setPaginate(Select2PaginateDTO paginate) {
        this.paginate = paginate;
    }
}
