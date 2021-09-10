/**
 * @author: Edson A. Terceros T.
 * 17
 */

package com.sales.market.dto;

import com.sales.market.model.Buy;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

public class BuyDto extends DtoBase<Buy> {

    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }


    //
    // contro  serv   ->   dto.setF1(model.getF1)
    // dto.setF2(model.getF2)
    // dto.setF7(model.getF7) //
    // new dto (m.f1, m.f2, f3, f4 ...m7.); //
    // practica Address a S f1, Intge f2  AddressTwo a2 S p1, int f2
    // mapper mapper.map(a, a2);
    // a2  a  a->a2   Typo y nombre
    // profundiad m.F1   mP1

    //anotaciones  @Target abc12ab, DTONEsted, abc12ab.b.l.z
    //f1

    //map  json

    @Override
    public BuyDto toDto(Buy buy, ModelMapper mapper) {
        /*BuyDto buyDto = new BuyDto();
        buyDto.setValue(buy.getValue());*/
        BuyDto dtoBase = super.toDto(buy, mapper);
        // buyDto.setTransacNum(Intger.valueOf(buy.getF1)     en caso que no coincide tipo o nombre de atributo
        return dtoBase;
    }

}
