package cl.duoc.LibraryFlow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_completo",nullable = false)
    private String fullName;
    @Column(name = "Correo_electronico",nullable = false)
    private String email;
    @Column(name = "telefono",nullable = true, length = 9)
    private String phone;
    @Column(name = "Fecha_inicio",nullable = false)
    private Date createdAt;
}
