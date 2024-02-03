package spring.boot.portfolio.Model.AboutMeModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Document("Growth") @AllArgsConstructor @NoArgsConstructor
public class GrowthCollection {
    @Id
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date endDate;
    private String location;
    private String details;
    private String etc;
}
