package com.kts.cultural_content.repository;

import com.kts.cultural_content.model.Fotografija;
import com.kts.cultural_content.model.Komentar;
import com.kts.cultural_content.model.KulturnaPonuda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotografijaRepository extends JpaRepository<Fotografija, Integer> {


    Fotografija findByKulturnaPonuda(KulturnaPonuda kulturnaPonuda);

    Fotografija findByKomentar(Komentar komentar);

}
