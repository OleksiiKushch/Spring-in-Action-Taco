package tacos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import tacos.udt.TacoUDT;
import tacos.udt.util.UDTUtils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Table ("orders")
public class TacoOrder {

    @PrimaryKey
    private UUID id = Uuids.timeBased();

    @NotBlank (message = "Delivery name is required")
    private String deliveryName;

    @NotBlank (message = "Street is required")
    private String deliveryStreet;

    @NotBlank (message = "City is required")
    private String deliveryCity;

    @NotBlank (message = "State is required")
    private String deliveryState;

    @NotBlank (message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber (message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern (regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits (integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private Date placedAt = new Date();

    @Column ("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(UDTUtils.toTacoUDT(taco));
    }
}
