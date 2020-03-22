package com.kammradt.twitter.bots.naruto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DollarDTO {

    private String buy;
    private String sell;
    private String variation;

}
