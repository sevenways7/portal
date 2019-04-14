package spring.web.application.portal.model.view;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CommentElement {
    private Integer id;
    private String text;
}
