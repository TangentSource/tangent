package io.belac.tangent.core;

import com.github.f4b6a3.tsid.Tsid;
import com.github.f4b6a3.tsid.TsidFactory;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdGenerator {

    private final TsidFactory factory;

    public IdGenerator(){
        factory = TsidFactory.builder().build();
    }

    public Tsid getId(){
        return factory.create();
    }

    public String getTsidString(){
        return getId().toString();
    }

    public Long getTsidLong(){
        return getId().toLong();
    }

}
