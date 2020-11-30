package com.kts.cultural_content.dto;

import com.kts.cultural_content.model.KulturnaPonuda;
import com.kts.cultural_content.model.RegistrovaniKorisnik;

import java.util.ArrayList;

public class RegistrovaniKorisnikDTO extends KorisnikDTO{

    private ArrayList<Integer> kulturnePonude;
    public RegistrovaniKorisnikDTO(){

    }
    public RegistrovaniKorisnikDTO(RegistrovaniKorisnik rk) {
        super(rk);
        for (KulturnaPonuda kp: rk.getKulturnaPonuda()) {
            kulturnePonude.add(kp.getId());
        }
    }
}
