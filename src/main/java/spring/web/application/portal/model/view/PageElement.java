package spring.web.application.portal.model.view;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PageElement {
    private Integer id;
    private String text;
    private List<CommentElement> comments;
}
