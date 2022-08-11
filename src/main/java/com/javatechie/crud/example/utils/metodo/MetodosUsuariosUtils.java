package com.javatechie.crud.example.utils.metodo;

import com.javatechie.crud.example.entity.Usuario;
import com.javatechie.crud.example.repository.UsuarioRepository;
import com.javatechie.crud.example.service.Impl.UsuarioServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class MetodosUsuariosUtils {

    private UsuarioRepository usuarioRepository;

    public MetodosUsuariosUtils(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /* public Usuario cambiarClave(String pass,String newPass,String newPassConf, Integer id) throws Exception {
        try {
           // Usuario user = usuarioServiceImpl.getById(id);
            if (!BCrypt.checkpw(pass, user.getPassword())) {
                if (newPass.equals(newPassConf)) {
                    user.setPassword(BCrypt.hashpw(pass, BCrypt.gensalt()));
                    user.setFechaMod(LocalDate.now());
                    user.setHoraMod(LocalDateTime.now());
                    usuarioRepository.save(user);
                    log.info("Se cambio la clave con exito");
                    return user;
                }else{
                    throw new Exception("Passwords no coinciden");
                }
            }else{
                throw new Exception("Password incorrecta");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    */

    public List<String> listarAlfabeticamenteNomApell(Page<Usuario> entities) {
        return entities.stream()
                .sorted(Comparator.comparing(e -> e.getApellido().concat(" " + e.getNombre())))
                .map(e -> e.getNombre().concat(" " + e.getApellido()))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    /**
     * construye el UserName(login) con la primer letra del nombre y concatena el apellido.
     *
     * @param nombre
     * @param apellido
     * @return
     * @throws Exception
     */
    public String crearUserName(String nombre, String apellido) throws Exception {
        try {
            nombre = nombre.substring(0, 1);
            return comprobarUserNameRepetido(nombre.concat(apellido).toUpperCase());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     * busca en la base de datos si el userName existe, en el caso de true le concatena un numero al final de manera ascendente, encaso de false lo persiste sin ningun cambio.
     *
     * @param userName
     * @return
     * @throws Exception
     */
    private String comprobarUserNameRepetido(String userName) throws Exception {
        try {
            return verificarUserName(userName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * SEPARA EL USERNAME EN LETRAS Y NUMEROS
     * @param userName
     * @return
     */
    private String verificarUserName(String userName) {
        String a = "";
        String b = "";
        String newUserName;

        char[] nameA = userName.toCharArray();
        for (char aux : nameA) {
            if (Character.isLetter(aux)) {
                a += aux;
            } else if (Character.isDigit(aux)) {
                b += aux;
            }
        }
        newUserName = a.concat(b);
        return existUserName(newUserName, a, b);
    }

    /**
     * COMPRUEBA QUE EL USERNAME NO EXISTA Y EN EL CASO QUE SI LE CONCATENA UN NUMERO O LE SUMA PARA DISCRIMINARLO
     * @param userName
     * @param user
     * @param numero
     * @return
     */
    private String existUserName(String userName, String user, String numero) {
        int contador = 0;
        boolean existe = true;
        do {
            if (usuarioRepository.findByLogin(userName) != null) {
                contador++;
                userName = user.concat(numero + contador);
            } else {
                existe = false;
            }
        } while (existe);
        return userName;
    }

}
