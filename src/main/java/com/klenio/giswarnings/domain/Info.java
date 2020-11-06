package com.klenio.giswarnings.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Info {
    private String date;
    private String description;
    private String link;

    @Override
    public boolean equals(Object o) {
        Info info = (Info) o;
        return date.equals(info.date) &&
                description.equals(info.description) &&
                link.equals(info.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, link);
    }
}
