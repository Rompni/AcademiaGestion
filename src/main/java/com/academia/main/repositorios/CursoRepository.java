package com.academia.main.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.academia.main.entidades.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE c.nivel = :nivel and c.etapa =:etapa" )
    Curso findCursoByNivelEtapa(
        @Param("nivel") String nivel,
        @Param("etapa") String etapa);

	List<Curso> findCursoByNivel(String string);
}
