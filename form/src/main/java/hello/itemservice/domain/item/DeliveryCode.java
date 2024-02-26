package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryCode {

    private String code;    // Fast/Normal/Slow
    private String displayName;

}
