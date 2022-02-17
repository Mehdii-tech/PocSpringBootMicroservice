package com.example.pocemainfo.services;

import com.example.pocemainfo.domain.Role;
import com.example.pocemainfo.domain.Utilisateur;
import com.example.pocemainfo.repo.RoleRepository;
import com.example.pocemainfo.repo.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Utilisateur saveUser(String email, String password) {
        Utilisateur utilisateur=utilisateurRepository.findByEmail(email);
        if(utilisateur!=null) throw new RuntimeException("User already exists");
        Utilisateur utilisateur1=new Utilisateur();
        utilisateur1.setEmail(email);
        utilisateur1.setPassword(bCryptPasswordEncoder.encode(password));
        utilisateurRepository.save(utilisateur1);
        addRoleToUser(email,"USER");
        return utilisateur1;
    }

    @Override
    public Role save(Role name) {
        return roleRepository.save(name);
    }

    @Override
    public Utilisateur loadUserByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public void addRoleToUser(String email, String name) {
        Utilisateur utilisateur1=utilisateurRepository.findByEmail(email);
        Role role1=roleRepository.findByName(name);
        utilisateur1.getRoles().add(role1);
    }
}
