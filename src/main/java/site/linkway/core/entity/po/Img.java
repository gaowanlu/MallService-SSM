package site.linkway.core.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Img {
    public String imgId;// varchar(32) PK
    public String imgType;// varchar(20)
    public int imgSize;// int UN
    public InputStream img;// mediumblob
}
