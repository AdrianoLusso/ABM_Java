package Modelo.Persona;

import java.sql.Date;

import Modelo.Tiposdoc.Tiposdoc;

public class Persona {

	private String nombre;
	private String apellido;
	private String nrodocumento;
	private int tipodocumento;
	private Date fechaNacimiento;
	private String sexo;
	private String estadoCivil;
	private String telefono;
	private String email;
	private Date iniOS;
	private Date finOS;
	private String calleDireccion;
	private int numeroDireccion;
	private String descripEstado;
	
	public Persona(String nrodocumento,int tipodocumento) {
		this.nrodocumento = nrodocumento;
		this.tipodocumento = tipodocumento;
	}
	
	//nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//apellido
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	//nrodocumento
	public String getNrodocumento() {
		return nrodocumento;
	}
	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}
	
	//tipodocumento
	public int getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento() {
		this.tipodocumento =  tipodocumento;
	}

	//sexo
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	//telefono
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	//email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescripEstado() {
		return descripEstado;
	}

	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}
	
	//otros
	
	//retorna los atributos de la persona en un arreglo.Debe respetar el orden de atributos que se muestran en el caso de uso de consulta.
	public Object[] getRow() {
		return new Object[]{nrodocumento,tipodocumento,apellido,nombre,fechaNacimiento,sexo,estadoCivil,
				telefono,email,iniOS,finOS,calleDireccion+" "+numeroDireccion,descripEstado};
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getIniOS() {
		return iniOS;
	}

	public void setIniOS(Date iniOS) {
		this.iniOS = iniOS;
	}

	public Date getFinOS() {
		return finOS;
	}

	public void setFinOS(Date finOS) {
		this.finOS = finOS;
	}

	public String getCalleDireccion() {
		return calleDireccion;
	}

	public void setCalleDireccion(String calleDireccion) {
		this.calleDireccion = calleDireccion;
	}

	public int getNumeroDireccion() {
		return numeroDireccion;
	}

	public void setNumeroDireccion(int numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}

	public void setTipodocumento(int tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", nrodocumento=" + nrodocumento
				+ ", tipodocumento=" + tipodocumento + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo
				+ ", estadoCivil=" + estadoCivil + ", telefono=" + telefono + ", email=" + email + ", iniOS=" + iniOS
				+ ", finOS=" + finOS + ", calleDireccion=" + calleDireccion + ", numeroDireccion=" + numeroDireccion
				+ ", descripEstado=" + descripEstado + "]";
	}

	
}
