package spring.web.application.portal.test;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RespModel {
    private String resp;
    private List<String> testItems;
}
