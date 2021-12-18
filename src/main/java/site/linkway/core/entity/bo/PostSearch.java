package site.linkway.core.entity.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSearch {
    String keyword;
    String searchType;
    MinMax price;
    int pageNow;
    int pageSize;
}
