package com.ai.mine.crystal.dto.resp;

import java.io.Serializable;
import java.util.List;

public class Select2ItemDTO implements Serializable {

    private String id;

    private String text;

    private List<Select2ItemDTO> children;

    private Boolean selected;

    private Boolean disabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Select2ItemDTO> getChildren() {
        return children;
    }

    public void setChildren(List<Select2ItemDTO> children) {
        this.children = children;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
