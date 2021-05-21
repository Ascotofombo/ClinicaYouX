package com.youx.clinica.Controller;

import com.youx.clinica.Model.Paciente;
import com.youx.clinica.Model.UF;
import com.youx.clinica.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/paciente")
public class PacienteController  {

    @Autowired
    PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Paciente>> getAllPacientes(@RequestParam(required = false) String title) {
        try {
            List<Paciente> pacientes = new ArrayList<Paciente>();

            pacienteRepository.findAll().forEach(pacientes::add);

            for(Paciente p : pacientes){
                p.setCpf(p.decodeCpf());
            }
            if (pacientes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQtdUF")
    public ResponseEntity<List<UF>> getQtdUF(@RequestParam(required = false) String title) {
        try {
            List<UF> ufs = new ArrayList<UF>();
            List<Paciente> ps = new ArrayList<Paciente>();
            pacienteRepository.findAll().forEach(ps::add);
            for(Paciente p : ps){
                Boolean tem = false;
                UF rep=new UF();
                for(UF uf : ufs){
                    System.out.println(uf.getLabel() + " = " + p.getUf());
                    if(uf.getLabel().equals(p.getUf())){
                        rep = uf;
                        tem = true;
                    }
                }
                if(tem){
                    Integer a = rep.getQtd();
                    a++;
                    UF newUF = new UF(p.getUf());
                    newUF.setQtd(a);
                    ufs.set(ufs.indexOf(rep),newUF);
                }else{
                    ufs.add(new UF(p.getUf()));
                }
                tem = false;
            }

            if (ufs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ufs, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByCPF/{cpf}")
    public ResponseEntity<Paciente> getPacienteByCPF(@PathVariable("cpf") String cpf) {

        Optional<Paciente> pacienteData = Optional.ofNullable(pacienteRepository.findByCpf(cpf));

        if (pacienteData.isPresent()) {
            return new ResponseEntity<>(pacienteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(path = "/newPaciente")
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        try {
          paciente.setCpf(paciente.encodeCpf());

          pacienteRepository.save(paciente);
          return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<HttpStatus> deletePaciente(@PathVariable("id") Integer id) {
        try {
            pacienteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletaTodos/")
    public ResponseEntity<HttpStatus> deleteAllPacientes() {
        try {
            pacienteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
