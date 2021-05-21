package com.youx.clinica.Repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.youx.clinica.Model.Paciente;
import com.youx.clinica.Model.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Paciente findByCpf(String cpf);
    Long deleteByCpf(String cpf);

}
