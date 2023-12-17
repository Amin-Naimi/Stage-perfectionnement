package stage.stage.Jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stage.stage.Repository.AdminRepository;
import stage.stage.Repository.EncadreurRepository;
import stage.stage.Repository.stagaireRepository;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustumorUserDetailService implements UserDetailsService {

    @Autowired
    stagaireRepository stagaireRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EncadreurRepository encadreurRepository;


    private stage.stage.entity.Stagaire stagaireDetail;
    private stage.stage.entity.Admin adminDetail;
    private stage.stage.entity.encadreur encadreurDetail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Inside LoadUserByUserName{}", email);
        stagaireDetail = stagaireRepository.findByEmailId(email);
        adminDetail = adminRepository.findByEmailId(email);
        encadreurDetail = encadreurRepository.findByEmailId(email);

        if (!Objects.isNull(stagaireDetail)) {
            stagaireDetail = stagaireRepository.findByEmailId(email);
            return new User(stagaireDetail.getEmail(), stagaireDetail.getPassword(), new ArrayList<>());
        } else if (!Objects.isNull(adminDetail)) {
            adminDetail = adminRepository.findByEmailId(email);
            return new User(adminDetail.getEmail(), adminDetail.getPassword(), new ArrayList<>());
        } else if (!Objects.isNull(encadreurDetail)) {
            encadreurDetail = encadreurRepository.findByEmailId(email);
            return new User(encadreurDetail.getEmail(), encadreurDetail.getPassword(), new ArrayList<>());

        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }


//La méthode getStagaireDetail() retourne les détails du stagiaire trouvé dans loadUserByUsername().

    public stage.stage.entity.Stagaire getStagaireDetail() {
        return stagaireDetail;
    }

    public stage.stage.entity.Admin getAdminDetail() {
        return adminDetail;
    }

    public stage.stage.entity.encadreur getEncadreurDetail() {
        return encadreurDetail;
    }
}
