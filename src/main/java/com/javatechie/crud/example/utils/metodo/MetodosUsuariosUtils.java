package com.javatechie.crud.example.utils.metodo;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.UsuarioRepository;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetodosUsuariosUtils {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    /**
     * comprueba que los capos no exsitan antes de modificar un usuario.
     */
    public boolean comprobarCampos(Usuario entity, Integer id) throws Exception {
        try {
            Optional<Usuario> optional = usuarioRepository.findById(id);
            Usuario user = optional.get();
            if ((usuarioRepository.findByMail(entity.getMail()).isEmpty()) || (entity.getMail().equals(user.getMail()))) {
                return false;
            } else if ((usuarioRepository.findByCelular(entity.getCelular()).isEmpty()) || (entity.getCelular() == user.getCelular())) {
                return false;
            } else if ((usuarioRepository.findByLegajo(entity.getLegajo()).isEmpty()) || (entity.getLegajo() == user.getLegajo())) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Usuario cambiarClave(String pass, Integer id) throws Exception {
        try {
            Usuario user = usuarioServiceImpl.findById(id);
            user.setPassword(BCrypt.hashpw(pass, BCrypt.gensalt()));
            user.setFechaMod(LocalDate.now());
            user.setHoraMod(LocalDateTime.now());
            usuarioRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<String> listarAlfabeticamenteNomApell(List<Usuario> entities) {
        return entities.stream()
                .sorted(Comparator.comparing(e -> e.getApellido().concat(" " + e.getNombre())))
                .map(e -> e.getNombre().concat(" " + e.getApellido()))
                .collect(Collectors.toList());
    }

    /**
     * busca en la base de datos si el userName existe, en el caso de true le concatena un numero al final de manera ascendente, encaso de false lo persiste sin ningun cambio.
     *
     * @param userName
     * @return
     * @throws Exception
     */
    public String comprobarUserNameRepetido(String userName) throws Exception {
        try {
            String a = "";
            String b = "";
            String newUserName;
            int contador = 0;
            boolean existe = true;
            char[] nameA = userName.toCharArray();
            for (char aux : nameA) {
                if (Character.isLetter(aux)) {
                    a += aux;
                } else if (Character.isDigit(aux)) {
                    b += aux;
                }
            }
            newUserName = a.concat(b);
            do {
                if (usuarioRepository.findByLogin(newUserName) != null) {
                    contador++;
                    newUserName = a.concat(b + contador);
                } else {
                    existe = false;
                }
            } while (existe);
            return newUserName;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
