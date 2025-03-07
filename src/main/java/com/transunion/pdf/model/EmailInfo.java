package com.transunion.pdf.model;

import lombok.*;
import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailInfo {
    private String email = DEFAULT_HYPHEN;
}
