/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.publicacionDGA.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "GEAC_PUBLICACION_DGA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeacPublicacionDga.findAll", query = "SELECT g FROM GeacPublicacionDga g")})
public class GeacPublicacionDga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PUBLICACION")
    private Long codigoPublicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_PERSONA")
    private Character tipoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(max = 30)
    @Column(name = "CEDULA_PERSONA")
    private String cedulaPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_PUBLICACION")
    private long tipoPublicacion;
    @Size(max = 500)
    @Column(name = "CAMPO1")
    private String campo1;
    @Size(max = 500)
    @Column(name = "CAMPO2")
    private String campo2;
    @Size(max = 500)
    @Column(name = "CAMPO3")
    private String campo3;
    @Size(max = 500)
    @Column(name = "CAMPO4")
    private String campo4;
    @Size(max = 500)
    @Column(name = "CAMPO5")
    private String campo5;
    @Size(max = 500)
    @Column(name = "CAMPO6")
    private String campo6;
    @Size(max = 500)
    @Column(name = "CAMPO7")
    private String campo7;
    @Size(max = 500)
    @Column(name = "CAMPO8")
    private String campo8;
    @Size(max = 500)
    @Column(name = "CAMPO9")
    private String campo9;
    @Size(max = 500)
    @Column(name = "CAMPO10")
    private String campo10;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FORMA_PUBLICACION")
    private Character formaPublicacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUSPICIO_PUBLICACION")
    private Character auspicioPublicacion;
    @Size(max = 100)
    @Column(name = "OTRO_AUSPICIO")
    private String otroAuspicio;
    @Size(max = 500)
    @Column(name = "OBSERVACION_PUBLICACION")
    private String observacionPublicacion;
    @Size(max = 100)
    @Column(name = "PUB_APELLIDO_AUTOR")
    private String pubApellidoAutor;
    @Size(max = 100)
    @Column(name = "PUB_NOMBRE_AUTOR")
    private String pubNombreAutor;
    @Size(max = 100)
    @Column(name = "PUB_APELLIDO_COAUTOR")
    private String pubApellidoCoautor;
    @Size(max = 100)
    @Column(name = "PUB_NOMBRE_COAUTOR")
    private String pubNombreCoautor;
    @Size(max = 200)
    @Column(name = "PUB_TITULO")
    private String pubTitulo;
    @Size(max = 200)
    @Column(name = "PUB_SUBTITULO")
    private String pubSubtitulo;
    @Size(max = 30)
    @Column(name = "PUB_EDICION")
    private String pubEdicion;
    @Column(name = "PUB_CIUEDITORIAL")
    private Long pubCiueditorial;
    @Column(name = "PUB_PAIEDITORIAL")
    private Short pubPaieditorial;
    @Size(max = 200)
    @Column(name = "PUB_EDITORIAL")
    private String pubEditorial;
    @Column(name = "PUB_FECEDICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubFecedicion;
    @Size(max = 30)
    @Column(name = "PUB_NUMPAGINAS")
    private String pubNumpaginas;
    @Size(max = 200)
    @Column(name = "PUB_TITREVISTA")
    private String pubTitrevista;
    @Column(name = "PUB_VOLUMEN")
    private Integer pubVolumen;
    @Size(max = 100)
    @Column(name = "PUB_NUMISBN")
    private String pubNumisbn;
    @Size(max = 100)
    @Column(name = "PUB_NUMISSN")
    private String pubNumissn;
    @Size(max = 1000)
    @Column(name = "PUB_URL")
    private String pubUrl;
    @Column(name = "PUB_IDIOMA")
    private Integer pubIdioma;
    @Size(max = 500)
    @Column(name = "PUB_NOMBRE_CAPITULO")
    private String pubNombreCapitulo;
    @Column(name = "PUB_PARTICIPACION")
    private Character pubParticipacion;
    @Column(name = "PUB_REVPARES")
    private Character pubRevpares;
    @Column(name = "PUB_ESTADO")
    private Character pubEstado;
    @Column(name = "PUB_BASEREVINDEXADA")
    private Character pubBaserevindexada;
    @Size(max = 200)
    @Column(name = "PUB_NOMOTRABASEINDEX")
    private String pubNomotrabaseindex;
    @Column(name = "PUB_PARTSERIE")
    private Character pubPartserie;
    @Size(max = 200)
    @Column(name = "PUB_ENTIDADSEDE")
    private String pubEntidadsede;
    @Column(name = "PUB_EVENTO")
    private Character pubEvento;
    @Column(name = "PUB_MEDDIFUSION")
    private Character pubMeddifusion;
    @Size(max = 200)
    @Column(name = "PUB_TITSERIE")
    private String pubTitserie;
    @Column(name = "PUB_ROL")
    private Long pubRol;
    @Column(name = "PUB_TIPOSVARIOS")
    private Long pubTiposvarios;
    @Column(name = "PUB_TIPOEDICION")
    private Long pubTipoedicion;
    @Column(name = "PUB_ALCANCE")
    private Long pubAlcance;
    @Column(name = "PUB_DURACION")
    private Long pubDuracion;
    @Column(name = "PUB_ANIOVISUALIZACION")
    private Long pubAniovisualizacion;
    @Size(max = 30)
    @Column(name = "PUB_ROLANFITRION")
    private String pubRolanfitrion;
    @Size(max = 30)
    @Column(name = "PUB_RANGPAGINA")
    private String pubRangpagina;
    @Size(max = 500)
    @Column(name = "PUB_EVIDENCIA")
    private String pubEvidencia;
    @Size(max = 300)
    @Column(name = "PUB_OBSERVACION")
    private String pubObservacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "USUARIO_CREA")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "USUARIO_ULTMODIFIC")
    private String usuarioUltmodific;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ULTMODIFIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltmodific;
    @JoinColumns({
        @JoinColumn(name = "CIUDAD_AUSPICIO", referencedColumnName = "CIU_CODIGO")
        , @JoinColumn(name = "PAIS_AUSPICIO", referencedColumnName = "PAI_CODIGO", insertable = false, updatable = false)})
    @ManyToOne
    private PrinCiudad prinCiudad;
    @JoinColumn(name = "PAIS_AUSPICIO", referencedColumnName = "PAI_CODIGO")
    @ManyToOne
    private PrinPais prinPais;
    @Transient
    private String nombrePERSONA;
    @Transient
    private String nomTipoContrato;
    @Transient
    private String nomDedicacion;
    @Transient
    private String nomArea;

    public GeacPublicacionDga() {
    }

    public String getdatbiblio() {
        String variable = "";
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        if (this.pubApellidoAutor == null && this.campo1 != null) {
            variable = null;
            switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
                case 1:
                case 2:
                case 4:
                case 5:
                    variable = (this.campo1 == null ? "" : "AUTOR: " + this.campo1 + " ");
                    break;
                case 3:
                case 6:
                    variable = (this.campo1 == null ? "" : "AUTOR: " + this.campo1 + " ");
                    break;
                default:
                    break;
            }
            switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
                case 1:
                case 2:
                case 4:
                case 5:
                    variable = variable + (this.campo2 == null ? "" : "TÍTULO: " + this.campo2 + " ");
                    break;
                case 3:

                    variable = variable + (this.campo2 == null ? "" : "CIUDAD: " + this.campo2 + " ");
                    break;
                case 6:
                    variable = variable + (this.campo2 == null ? "" : "AUTOR: " + this.campo2 + " ");
                    break;
                default:
                    break;
            }
            switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
                case 1:
                case 2:
                    variable = variable + (this.campo3 == null ? "" : "PIE DE IMPRENTA: " + this.campo3 + " ");
                    break;
                case 3:
                    variable = variable + (this.campo3 == null ? "" : "PUBLICACIÓN: " + this.campo3 + " ");
                    break;
                case 4:
                    variable = variable + (this.campo3 == null ? "" : "EN: " + this.campo3 + " ");
                    break;
                case 5:
                    variable = variable + (this.campo3 == null ? "" : "CIUDAD EDITORIAL: " + this.campo3 + " ");
                    break;
                default:
                    break;
            }
            switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
                case 1:
                case 2:
                    variable = variable + (this.campo4 == null ? "" : "DESCRIPCIÓN FÍSICA: " + this.campo4 + " ");
                    break;
                case 3:
                    variable = variable + (this.campo4 == null ? "" : "COLECCION O SERIE: " + this.campo4 + " ");
                    break;
                case 4:
                    variable = variable + (this.campo4 == null ? "" : "PIE DE IMPRENTA: " + this.campo4 + " ");
                    break;
                case 5:
                case 6:
                    variable = variable + (this.campo4 == null ? "" : "PUBLICACIÓN: " + this.campo4 + " ");
                    break;
                default:
                    break;
            }
            switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
                case 1:

                    variable = variable + (this.campo5 == null ? "" : "COLECCION O SERIE: " + this.campo5 + " ");
                    break;
                case 2:
                    variable = variable + (this.campo5 == null ? "" : "OTROS AUTORES: " + this.campo5 + " ");
                    break;
                case 4:
                    variable = variable + (this.campo5 == null ? "" : "DESCRIPCIÓN FÍSICA: " + this.campo5 + " ");
                    break;

                default:
                    break;
            }
            variable = variable + (this.campo10 == null ? "" : " AÑO: " + this.campo10);
        } else {
            variable = null;
            variable = (this.pubApellidoAutor == null ? "" : "AUTOR: " + this.pubApellidoAutor) + " " + (this.pubNombreAutor == null ? "" : this.pubNombreAutor);
            variable = variable + (this.pubApellidoCoautor == null ? "" : " Coautor: " + this.pubApellidoCoautor) + " " + (this.pubNombreCoautor == null ? "" : this.pubNombreCoautor);
            variable = variable + (this.pubTitulo == null ? "" : " Título: " + this.pubTitulo);
            variable = variable + (this.pubSubtitulo == null ? "" : " Subtítulo: " + this.pubSubtitulo);
            variable = variable + (this.pubEdicion == null ? "" : " Edición: " + this.pubEdicion);
            variable = variable + (this.pubFecedicion == null ? "" : " Fecha Edición: " + formateador.format(this.pubFecedicion));
            variable = variable + (this.pubNumpaginas == null ? "" : " Número Pág.:  " + this.pubNumpaginas);
            variable = variable + (this.tipoPublicacion == 5 || this.tipoPublicacion == 6 ? "Título: " + (this.pubTitrevista == null ? "" : this.pubTitrevista) : "");
            variable = variable + (this.pubVolumen == null ? "" : " Volumén:  " + this.pubVolumen);
            variable = variable + (this.pubNumissn == null ? "" : " ISSN: " + this.pubNumissn);
            variable = variable + (this.pubNumisbn == null ? "" : " ISBN: " + this.pubNumisbn);
            variable = variable + (this.formaPublicacion == 'D' ? " Digital " + (this.pubUrl == null ? "" : this.pubUrl) : " Impreso ");
        }

        return variable;
    }

    public String getnomtippublicacion() {
        String variable = "";
        switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
            case 1:
                variable = "Libros de autoría personal";
                break;
            case 2:
                variable = "Libros  que actúa como editor, compilador o coordinador";
                break;
            case 3:
                variable = "Participación como miembro del Comité Editorial";
                break;
            case 4:
                variable = "Autoría de capítulo dentro de un libro colectivo";
                break;
            case 5:
                variable = "Publicación de artículos en revistas académicas indexadas";
                break;
            case 6:
                variable = "Publicación de artículos en revistas académicas no indexadas";
                break;
            case 7:
            case 11:
                variable = "Ponencia con formato de artículo académico";
                break;
            case 8:
                variable = "Página Web";
                break;
            case 9:
                variable = "Tesis";
                break;
            case 10:
                variable = "Otro";
                break;
            case 13:
                variable = "Libro";
                break;
            case 14:
                variable = "Revistas";
                break;
            case 15:
                variable = "Ponencias";
                break;
            case 16:
                variable = "Productos Audiovisuales";
                break;
            case 17:
                variable = "Otra Publicación";
                break;
            default:
                variable = "Otro";
                break;
        }
        return variable;
    }

    public String getnomformpub() {
        String variable = "";
        switch (this.formaPublicacion) {
            case 'I':
                variable = "Impreso";
                break;
            case 'D':
                variable = "Digital";
                break;
            default:
                variable = "Otro";
                break;
        }
        return variable;
    }

    public String getnomausp() {
        String variable = "";
        switch (this.auspicioPublicacion) {
            case 'U':
                variable = "UASB";
                break;
            case 'O':
                variable = "Otro(Especifique)";
                break;
            case 'N':
                variable = "N/A";
                break;
            default:
                variable = "N/A";
                break;
        }
        return variable;
    }

    public String getnomsubtippub() {
        String variable = "";
        switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
            case 17:
                switch (this.pubParticipacion) {
                    case 'P':
                        variable = "Papers";
                        break;
                    case 'I':
                        variable = "Informes";
                        break;
                    case 'O':
                        variable = "Otros";
                        break;
                }
                break;
        }
        return variable;
    }

    public String getnomtippart() {
        String variable = "";
        if (pubParticipacion != null) {
            switch (this.pubParticipacion) {
                case 'A':
                    variable = "Autor";
                    break;
                case 'C':
                    variable = "Coautor";
                    break;
                case 'E':
                    variable = "Editor";
                    break;
                case 'D':
                    variable = "Coordinador";
                    break;
                case 'T':
                    variable = "Traductor";
                    break;
                case 'M':
                    variable = "Compilador";
                    break;
                default:
                    variable = "N/A";
                    break;
            }
        }
        return variable;
    }

    public String getnomidioma() {
        String variable = "";
        if (pubIdioma != null) {
            switch (Integer.parseInt(String.valueOf(this.pubIdioma))) {
                case 1:
                    variable = "Español";
                    break;
                case 2:
                    variable = "Inglés";
                    break;
                case 3:
                    variable = "Francés";
                    break;
                case 4:
                    variable = "Italiano";
                    break;
                case 5:
                    variable = "Portugués";
                    break;
                case 6:
                    variable = "Otro";
                    break;
                default:
                    variable = "Otro";
                    break;
            }
        }
        return variable;
    }

    public String getDesRol() {
        String variable = "";
        switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
            case 13:
                switch (Integer.parseInt(String.valueOf(pubRol))) {
                    case 1:
                        variable = "Autor";
                        break;
                    case 2:
                        variable = "Coautor";
                        break;
                    case 3:
                        variable = "Editor";
                        break;
                    case 4:
                        variable = "Compilador";
                        break;
                    case 5:
                        variable = "Revisor";
                        break;
                    case 6:
                        variable = "Coordinador de Informe Periódico";
                        break;
                    default:
                        break;
                }
                break;
            case 14:
                switch (Integer.parseInt(String.valueOf(pubRol))) {
                    case 1:
                        variable = "Autor";
                        break;
                    case 2:
                        variable = "Coautor";
                        break;
                    case 3:
                        variable = "Revisor";
                        break;
                    case 4:
                        variable = "Editor";
                        break;
                    case 5:
                        variable = "Director";
                        break;
                    case 6:
                        variable = "Coordinador Número Monográfico";
                        break;
                    default:
                        break;
                }
                break;
            case 15:
                switch (Integer.parseInt(String.valueOf(pubRol))) {
                    case 1:
                        variable = "Autor";
                        break;
                    case 2:
                        variable = "Coautor";
                        break;
                    case 3:
                        variable = "Moderador";
                        break;
                    case 4:
                        variable = "Ponente";
                        break;
                    case 5:
                        variable = "Expositor";
                        break;
                    case 6:
                        variable = "Conferenciante";
                        break;
                    default:
                        break;
                }
                break;
            case 16:
                switch (Integer.parseInt(String.valueOf(pubRol))) {
                    case 1:
                        variable = "Autor";
                        break;
                    case 2:
                        variable = "Coautor";
                        break;
                    default:
                        break;
                }
                break;
            case 17:
                switch (Integer.parseInt(String.valueOf(pubRol))) {
                    case 1:
                        variable = "Autor";
                        break;
                    case 2:
                        variable = "Coautor";
                        break;
                    case 3:
                        variable = "Editor";
                        break;
                    case 4:
                        variable = "Otro";
                        break;
                    default:
                        break;
                }
                break;
            default:
                variable = "Otro";
                break;
        }
        return variable;
    }

    public String getDesAlcance() {
        String alcance = "";
        if (pubAlcance != null) {
            switch (Integer.parseInt(String.valueOf(pubAlcance))) {
                case 1:
                    alcance = "NACIONAL";
                    break;
                case 2:
                    alcance = "INTERNACIONAL";
                    break;
                default:
                    break;
            }
        }
        return alcance;
    }

    public String getDatosBibliograficos() {
        String datos = "";
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        switch (Integer.parseInt(String.valueOf(tipoPublicacion))) {
            case 13:
                datos += (this.pubApellidoAutor == null ? "" : "AUTOR: " + this.pubApellidoAutor) + " " + (this.pubNombreAutor == null ? "" : this.pubNombreAutor);
                datos += (this.pubTitulo == null ? "" : " TÍTULO: " + this.pubTitulo);
                datos += (this.campo10 == null ? "" : " AÑO DE PUBLICACIÓN: " + this.campo10);
                datos += (this.pubAlcance == null ? "" : " ALCANCE: " + this.getDesAlcance());
                datos += (this.pubNombreCapitulo == null ? "" : " TÍTULO DEL CAPÍTULO: " + this.pubNombreCapitulo);
                datos += (this.pubRolanfitrion == null ? "" : " ROL ANFITRION: " + this.pubRolanfitrion);
                datos += (this.pubRangpagina == null ? "" : " RANGO PAGINAS: " + this.pubRangpagina);
                datos += (this.pubEditorial == null ? "" : " EDITORIAL: " + this.pubEditorial);
                datos += (this.pubNumpaginas == null ? "" : " N° DE PÁGINAS: " + this.pubNumpaginas);
                datos += (this.pubNumisbn == null ? "" : " N° ISBN: " + this.pubNumisbn);
                datos += (this.pubFecedicion == null ? "" : " FECHA EDICIÓN: " + formateador.format(this.pubFecedicion));
                datos += (String.valueOf(this.auspicioPublicacion) == null ? "" : " AUSPICIO PUBLICACIÓN: " + this.getDesAuspicio());
                datos += (this.otroAuspicio == null ? "" : " DETALLE: " + this.otroAuspicio);
                datos += (String.valueOf(this.formaPublicacion) == null ? "" : " FORMA PUBLICACIÓN: " + this.getnomformpub().toUpperCase());
                datos += (this.pubPartserie == null ? "" : " PARTE DE UNA SERIE: " + getParteSerie());
                datos += (this.pubTitserie == null ? "" : " TÍTULO SERIE: " + this.pubTitserie);
                datos += (this.pubRevpares == null ? "" : " REVISIÓN PARES: " + getRevisionPares());
                datos += (this.pubTipoedicion == null ? "" : " EDICIÓN: " + getDesTipoEdicion());
                datos += (this.pubTiposvarios == null ? "" : " TIPO: " + getTiposVarios());
                break;
            case 14:
                datos += (this.pubApellidoAutor == null ? "" : "AUTOR: " + this.pubApellidoAutor) + " " + (this.pubNombreAutor == null ? "" : this.pubNombreAutor);
                datos += (this.pubTitulo == null ? "" : " TÍTULO: " + this.pubTitulo);
                datos += (this.campo10 == null ? "" : " AÑO DE PUBLICACIÓN: " + this.campo10);
                datos += (this.pubAlcance == null ? "" : " ALCANCE: " + this.getDesAlcance());
                datos += (this.campo8 == null ? "" : " NOMBRE DE REVISTA: " + this.campo8);
                datos += (this.campo1 == null ? "" : " NÚMERO REVISTA: " + this.campo1);
                datos += (this.campo2 == null ? "" : " PERIODICIDAD: " + this.campo2);
                datos += (this.pubVolumen == null ? "" : " VOLUMEN: " + this.pubVolumen);
                datos += (this.campo7 == null ? "" : " AÑO DE EDICIÓN: " + this.campo7);
                datos += (this.pubEditorial == null ? "" : " EDITORIAL: " + this.pubEditorial);
                datos += (this.pubNumpaginas == null ? "" : " N° DE PÁGINAS: " + this.pubNumpaginas);
                datos += (this.pubFecedicion == null ? "" : " FECHA EDICIÓN: " + formateador.format(this.pubFecedicion));
                datos += (this.pubNumissn == null ? "" : " N° ISSN: " + this.pubNumissn);
                datos += (String.valueOf(this.formaPublicacion) == null ? "" : " FORMA PUBLICACIÓN: " + this.getnomformpub().toUpperCase());
                datos += (this.pubTiposvarios == null ? "" : " BASE INDEXACIÓN: " + getBaseIndexacion());
                datos += (this.campo6 == null ? "" : " DETALLE BASE INDEXACIÓN: " + this.campo6);
                break;
            case 15:
                datos += (this.pubApellidoAutor == null ? "" : "AUTOR: " + this.pubApellidoAutor) + " " + (this.pubNombreAutor == null ? "" : this.pubNombreAutor);
                datos += (this.pubTitulo == null ? "" : " TÍTULO: " + this.pubTitulo);
                datos += (this.campo10 == null ? "" : " AÑO DE PRESENTACIÓN: " + this.campo10);
                datos += (this.pubAlcance == null ? "" : " ALCANCE: " + this.getDesAlcance());
                datos += (this.campo5 == null ? "" : " NOMBRE EVENTO: " + this.campo5);
                datos += (this.pubNumpaginas == null ? "" : " N° DE PÁGINAS: " + this.pubNumpaginas);
                datos += (this.pubEntidadsede == null ? "" : " ENTIDAD SEDE: " + this.pubEntidadsede);
                datos += (this.pubMeddifusion == null ? "" : " MECANISMOS DIFUSIÓN: " + getDesMecanismoDif());
                datos += (this.pubFecedicion == null ? "" : " FECHA DE ACTIVIDAD: " + formateador.format(this.pubFecedicion));
                datos += (this.pubEvento == null ? "" : " TIPO ACTIVIDAD: " + getEnEvento());
                break;
            case 16:
                datos += (this.pubApellidoAutor == null ? "" : "AUTOR: " + this.pubApellidoAutor) + " " + (this.pubNombreAutor == null ? "" : this.pubNombreAutor);
                datos += (this.pubTitulo == null ? "" : " TÍTULO: " + this.pubTitulo);
                datos += (this.campo10 == null ? "" : " AÑO DE PRESENTACIÓN: " + this.campo10);
                datos += (this.pubAlcance == null ? "" : " ALCANCE: " + this.getDesAlcance());
                datos += (this.pubFecedicion == null ? "" : " FECHA DE PRESENTACIÓN: " + formateador.format(this.pubFecedicion));
                datos += (this.pubDuracion == null ? "" : "DURACIÓN: " + getDesDuracion());
                break;
            case 17:
                datos += (this.pubApellidoAutor == null ? "" : "AUTOR: " + this.pubApellidoAutor) + " " + (this.pubNombreAutor == null ? "" : this.pubNombreAutor);
                datos += (this.pubTitulo == null ? "" : " TÍTULO: " + this.pubTitulo);
                datos += (this.pubParticipacion == null ? "" : " TIPO: " + this.getnomsubtippub());
                datos += (this.campo10 == null ? "" : " AÑO DE PRESENTACIÓN: " + this.campo10);
                datos += (this.pubRol == null ? "" : " Rol: " + this.getDesRol());
                datos += (this.pubFecedicion == null ? "" : " FECHA DE PRESENTACIÓN: " + formateador.format(this.pubFecedicion));
                datos += (this.pubUrl == null ? "" : "DOI/URI/URL:: " + this.pubUrl);
                break;
            default:
                datos = (this.pubApellidoAutor == null ? "" : "AUTOR: " + this.pubApellidoAutor) + " " + (this.pubNombreAutor == null ? "" : this.pubNombreAutor);
                datos = datos + (this.pubApellidoCoautor == null ? "" : " Coautor: " + this.pubApellidoCoautor) + " " + (this.pubNombreCoautor == null ? "" : this.pubNombreCoautor);
                datos = datos + (this.pubTitulo == null ? "" : " Título: " + this.pubTitulo);
                datos = datos + (this.pubSubtitulo == null ? "" : " Subtítulo: " + this.pubSubtitulo);
                datos = datos + (this.pubEdicion == null ? "" : " Edición: " + this.pubEdicion);
                datos = datos + (this.pubFecedicion == null ? "" : " Fecha Edición: " + formateador.format(this.pubFecedicion));
                datos = datos + (this.pubNumpaginas == null ? "" : " Número Pág.:  " + this.pubNumpaginas);
                datos = datos + (this.tipoPublicacion == 5 || this.tipoPublicacion == 6 ? "Título: " + (this.pubTitrevista == null ? "" : this.pubTitrevista) : "");
                datos = datos + (this.pubVolumen == null ? "" : " Volumén:  " + this.pubVolumen);
                datos = datos + (this.pubNumissn == null ? "" : " ISSN: " + this.pubNumissn);
                datos = datos + (this.pubNumisbn == null ? "" : " ISBN: " + this.pubNumisbn);
                datos = datos + (this.formaPublicacion == 'D' ? " Digital " + (this.pubUrl == null ? "" : this.pubUrl) : " Impreso ");
                break;
        }
        return datos;
    }

    public String getDesDuracion() {
        String duracion = "";
        if (this.pubDuracion != null) {
            switch (Integer.parseInt(String.valueOf(this.pubDuracion))) {
                case 1:
                    duracion = "HASTA 30 MINUTOS";
                    break;
                case 2:
                    duracion = "MÁS DE 30 MINUTOS";
                    break;
                default:
                    break;
            }
        }
        return duracion;
    }

    public String getEnEvento() {
        String evento = "";
        if (this.pubEvento != null) {
            switch (this.pubEvento) {
                case 'C':
                    evento = "Congreso";
                    break;
                case 'S':
                    evento = "Seminario";
                    break;
                case 'I':
                    evento = "Simposio";
                    break;
                case 'O':
                    evento = "Conferencia";
                    break;
                case 'E':
                    evento = "Encuentro";
                    break;
                case 'N':
                    evento = "Conversatorio";
                    break;
                case 'L':
                    evento = "Coloquio";
                    break;
                case 'M':
                    evento = "Mesa Redonda";
                    break;
                case 'F':
                    evento = "Foro";
                    break;
                case 'T':
                    evento = "Taller de Discusión";
                    break;
                case 'G':
                    evento = "Grupo de Discusión";
                    break;
                case 'P':
                    evento = "Panel";
                    break;

                default:
                    break;
            }
        }
        return evento;
    }

    public String getDesMecanismoDif() {
        String mecanismo = "";
        if (this.pubMeddifusion != null) {
            switch (this.pubMeddifusion) {
                case 'D':
                    mecanismo = "DISCUSIÓN EN EL FORO";
                    break;
                case 'M':
                    mecanismo = "MEMORIA";
                    break;
                case 'P':
                    mecanismo = "PÁGINA WEB";
                    break;
                default:
                    break;
            }
        }
        return mecanismo;
    }

    public String getBaseIndexacion() {
        String base = "";
        if (this.pubTiposvarios != null) {
            switch (Integer.parseInt(String.valueOf(this.pubTiposvarios))) {
                case 1:
                    base = "LATINDEX";
                    break;
                case 2:
                    base = "NINGUNO";
                    break;
                case 3:
                    base = "OTRO";
                    break;
                case 4:
                    base = "SCOPUS";
                    break;
                case 5:
                    base = "SCIMAGO";
                    break;
                case 6:
                    base = "ISI WEB";
                    break;
                case 7:
                    base = "SCIELO";
                    break;
                default:
                    break;
            }
        }
        return base;
    }

    public String getTiposVarios() {
        String tiposvarios = "";
        if (this.pubTiposvarios != null) {
            switch (Integer.parseInt(String.valueOf(this.pubTiposvarios))) {
                case 1:
                    tiposvarios = "ACADÉMICO";
                    break;
                case 2:
                    tiposvarios = "NO ACADÉMICO";
                    break;
                default:
                    break;
            }
        }
        return tiposvarios;
    }

    public String getDesTipoEdicion() {
        String tipoedicion = "";
        if (this.pubTipoedicion != null) {
            switch (Integer.parseInt(String.valueOf(this.pubTipoedicion))) {
                case 1:
                    tipoedicion = "PRIMERA";
                    break;
                case 2:
                    tipoedicion = "REEDICIÓN";
                    break;
                case 3:
                    tipoedicion = "RECOPILACIÓN";
                    break;
                default:
                    break;
            }
        }
        return tipoedicion;
    }

    public String getRevisionPares() {
        String pares = "";
        if (this.pubRevpares != null) {
            switch (this.pubRevpares) {
                case 'S':
                    pares = "SI";
                    break;
                case 'N':
                    pares = "NO";
                    break;
                default:
                    break;
            }
        }
        return pares;
    }

    public String getParteSerie() {
        String serie = "";
        if (this.pubPartserie != null) {
            switch (this.pubPartserie) {
                case '1':
                    serie = "SI";
                    break;
                case '0':
                    serie = "NO";
                    break;
                default:
                    break;
            }
        }
        return serie;
    }

    public String getDesAuspicio() {
        String auspicio = "";
        if (String.valueOf(this.auspicioPublicacion) != null) {
            switch (this.auspicioPublicacion) {
                case 'U':
                    auspicio = "UASB";
                    break;
                case 'O':
                    auspicio = "OTRO";
                    break;
                case 'N':
                    auspicio = "N/A";
                    break;
                default:
                    auspicio = "N/A";
                    break;
            }
        }
        return auspicio;
    }

    public GeacPublicacionDga(Long codigoPublicacion) {
        this.codigoPublicacion = codigoPublicacion;
    }

    public GeacPublicacionDga(Long codigoPublicacion, Character tipoPersona, String cedulaPersona, long tipoPublicacion, Character formaPublicacion, Character auspicioPublicacion, String usuarioCrea, String usuarioUltmodific, Date fechaCrea, Date fechaUltmodific) {
        this.codigoPublicacion = codigoPublicacion;
        this.tipoPersona = tipoPersona;
        this.cedulaPersona = cedulaPersona;
        this.tipoPublicacion = tipoPublicacion;
        this.formaPublicacion = formaPublicacion;
        this.auspicioPublicacion = auspicioPublicacion;
        this.usuarioCrea = usuarioCrea;
        this.usuarioUltmodific = usuarioUltmodific;
        this.fechaCrea = fechaCrea;
        this.fechaUltmodific = fechaUltmodific;
    }

    public Long getCodigoPublicacion() {
        return codigoPublicacion;
    }

    public void setCodigoPublicacion(Long codigoPublicacion) {
        this.codigoPublicacion = codigoPublicacion;
    }

    public Character getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(Character tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public long getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(long tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    public String getCampo4() {
        return campo4;
    }

    public void setCampo4(String campo4) {
        this.campo4 = campo4;
    }

    public String getCampo5() {
        return campo5;
    }

    public void setCampo5(String campo5) {
        this.campo5 = campo5;
    }

    public String getCampo6() {
        return campo6;
    }

    public void setCampo6(String campo6) {
        this.campo6 = campo6;
    }

    public String getCampo7() {
        return campo7;
    }

    public void setCampo7(String campo7) {
        this.campo7 = campo7;
    }

    public String getCampo8() {
        return campo8;
    }

    public void setCampo8(String campo8) {
        this.campo8 = campo8;
    }

    public String getCampo9() {
        return campo9;
    }

    public void setCampo9(String campo9) {
        this.campo9 = campo9;
    }

    public String getCampo10() {
        return campo10;
    }

    public void setCampo10(String campo10) {
        this.campo10 = campo10;
    }

    public Character getFormaPublicacion() {
        return formaPublicacion;
    }

    public void setFormaPublicacion(Character formaPublicacion) {
        this.formaPublicacion = formaPublicacion;
    }

    public Character getAuspicioPublicacion() {
        return auspicioPublicacion;
    }

    public void setAuspicioPublicacion(Character auspicioPublicacion) {
        this.auspicioPublicacion = auspicioPublicacion;
    }

    public String getOtroAuspicio() {
        return otroAuspicio;
    }

    public void setOtroAuspicio(String otroAuspicio) {
        this.otroAuspicio = otroAuspicio;
    }

    public String getObservacionPublicacion() {
        return observacionPublicacion;
    }

    public void setObservacionPublicacion(String observacionPublicacion) {
        this.observacionPublicacion = observacionPublicacion;
    }

    public String getPubApellidoAutor() {
        return pubApellidoAutor;
    }

    public void setPubApellidoAutor(String pubApellidoAutor) {
        this.pubApellidoAutor = pubApellidoAutor;
    }

    public String getPubNombreAutor() {
        return pubNombreAutor;
    }

    public void setPubNombreAutor(String pubNombreAutor) {
        this.pubNombreAutor = pubNombreAutor;
    }

    public String getPubApellidoCoautor() {
        return pubApellidoCoautor;
    }

    public void setPubApellidoCoautor(String pubApellidoCoautor) {
        this.pubApellidoCoautor = pubApellidoCoautor;
    }

    public String getPubNombreCoautor() {
        return pubNombreCoautor;
    }

    public void setPubNombreCoautor(String pubNombreCoautor) {
        this.pubNombreCoautor = pubNombreCoautor;
    }

    public String getPubTitulo() {
        return pubTitulo;
    }

    public void setPubTitulo(String pubTitulo) {
        this.pubTitulo = pubTitulo;
    }

    public String getPubSubtitulo() {
        return pubSubtitulo;
    }

    public void setPubSubtitulo(String pubSubtitulo) {
        this.pubSubtitulo = pubSubtitulo;
    }

    public String getPubEdicion() {
        return pubEdicion;
    }

    public void setPubEdicion(String pubEdicion) {
        this.pubEdicion = pubEdicion;
    }

    public Long getPubCiueditorial() {
        return pubCiueditorial;
    }

    public void setPubCiueditorial(Long pubCiueditorial) {
        this.pubCiueditorial = pubCiueditorial;
    }

    public Short getPubPaieditorial() {
        return pubPaieditorial;
    }

    public void setPubPaieditorial(Short pubPaieditorial) {
        this.pubPaieditorial = pubPaieditorial;
    }

    public String getPubEditorial() {
        return pubEditorial;
    }

    public void setPubEditorial(String pubEditorial) {
        this.pubEditorial = pubEditorial;
    }

    public Date getPubFecedicion() {
        return pubFecedicion;
    }

    public void setPubFecedicion(Date pubFecedicion) {
        this.pubFecedicion = pubFecedicion;
    }

    public String getPubNumpaginas() {
        return pubNumpaginas;
    }

    public void setPubNumpaginas(String pubNumpaginas) {
        this.pubNumpaginas = pubNumpaginas;
    }

    public String getPubTitrevista() {
        return pubTitrevista;
    }

    public void setPubTitrevista(String pubTitrevista) {
        this.pubTitrevista = pubTitrevista;
    }

    public Integer getPubVolumen() {
        return pubVolumen;
    }

    public void setPubVolumen(Integer pubVolumen) {
        this.pubVolumen = pubVolumen;
    }

    public String getPubNumisbn() {
        return pubNumisbn;
    }

    public void setPubNumisbn(String pubNumisbn) {
        this.pubNumisbn = pubNumisbn;
    }

    public String getPubNumissn() {
        return pubNumissn;
    }

    public void setPubNumissn(String pubNumissn) {
        this.pubNumissn = pubNumissn;
    }

    public String getPubUrl() {
        return pubUrl;
    }

    public void setPubUrl(String pubUrl) {
        this.pubUrl = pubUrl;
    }

    public Integer getPubIdioma() {
        return pubIdioma;
    }

    public void setPubIdioma(Integer pubIdioma) {
        this.pubIdioma = pubIdioma;
    }

    public String getPubNombreCapitulo() {
        return pubNombreCapitulo;
    }

    public void setPubNombreCapitulo(String pubNombreCapitulo) {
        this.pubNombreCapitulo = pubNombreCapitulo;
    }

    public Character getPubParticipacion() {
        return pubParticipacion;
    }

    public void setPubParticipacion(Character pubParticipacion) {
        this.pubParticipacion = pubParticipacion;
    }

    public Character getPubRevpares() {
        return pubRevpares;
    }

    public void setPubRevpares(Character pubRevpares) {
        this.pubRevpares = pubRevpares;
    }

    public Character getPubEstado() {
        return pubEstado;
    }

    public void setPubEstado(Character pubEstado) {
        this.pubEstado = pubEstado;
    }

    public Character getPubBaserevindexada() {
        return pubBaserevindexada;
    }

    public void setPubBaserevindexada(Character pubBaserevindexada) {
        this.pubBaserevindexada = pubBaserevindexada;
    }

    public String getPubNomotrabaseindex() {
        return pubNomotrabaseindex;
    }

    public void setPubNomotrabaseindex(String pubNomotrabaseindex) {
        this.pubNomotrabaseindex = pubNomotrabaseindex;
    }

    public Character getPubPartserie() {
        return pubPartserie;
    }

    public void setPubPartserie(Character pubPartserie) {
        this.pubPartserie = pubPartserie;
    }

    public String getPubEntidadsede() {
        return pubEntidadsede;
    }

    public void setPubEntidadsede(String pubEntidadsede) {
        this.pubEntidadsede = pubEntidadsede;
    }

    public Character getPubEvento() {
        return pubEvento;
    }

    public void setPubEvento(Character pubEvento) {
        this.pubEvento = pubEvento;
    }

    public Character getPubMeddifusion() {
        return pubMeddifusion;
    }

    public void setPubMeddifusion(Character pubMeddifusion) {
        this.pubMeddifusion = pubMeddifusion;
    }

    public String getPubTitserie() {
        return pubTitserie;
    }

    public void setPubTitserie(String pubTitserie) {
        this.pubTitserie = pubTitserie;
    }

    public Long getPubRol() {
        return pubRol;
    }

    public void setPubRol(Long pubRol) {
        this.pubRol = pubRol;
    }

    public Long getPubTiposvarios() {
        return pubTiposvarios;
    }

    public void setPubTiposvarios(Long pubTiposvarios) {
        this.pubTiposvarios = pubTiposvarios;
    }

    public Long getPubTipoedicion() {
        return pubTipoedicion;
    }

    public void setPubTipoedicion(Long pubTipoedicion) {
        this.pubTipoedicion = pubTipoedicion;
    }

    public Long getPubAlcance() {
        return pubAlcance;
    }

    public void setPubAlcance(Long pubAlcance) {
        this.pubAlcance = pubAlcance;
    }

    public Long getPubDuracion() {
        return pubDuracion;
    }

    public void setPubDuracion(Long pubDuracion) {
        this.pubDuracion = pubDuracion;
    }

    public Long getPubAniovisualizacion() {
        return pubAniovisualizacion;
    }

    public void setPubAniovisualizacion(Long pubAniovisualizacion) {
        this.pubAniovisualizacion = pubAniovisualizacion;
    }

    public String getPubRolanfitrion() {
        return pubRolanfitrion;
    }

    public void setPubRolanfitrion(String pubRolanfitrion) {
        this.pubRolanfitrion = pubRolanfitrion;
    }

    public String getPubRangpagina() {
        return pubRangpagina;
    }

    public void setPubRangpagina(String pubRangpagina) {
        this.pubRangpagina = pubRangpagina;
    }

    public String getPubEvidencia() {
        return pubEvidencia;
    }

    public void setPubEvidencia(String pubEvidencia) {
        this.pubEvidencia = pubEvidencia;
    }

    public String getPubObservacion() {
        return pubObservacion;
    }

    public void setPubObservacion(String pubObservacion) {
        this.pubObservacion = pubObservacion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getUsuarioUltmodific() {
        return usuarioUltmodific;
    }

    public void setUsuarioUltmodific(String usuarioUltmodific) {
        this.usuarioUltmodific = usuarioUltmodific;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Date getFechaUltmodific() {
        return fechaUltmodific;
    }

    public void setFechaUltmodific(Date fechaUltmodific) {
        this.fechaUltmodific = fechaUltmodific;
    }

    public PrinCiudad getPrinCiudad() {
        return prinCiudad;
    }

    public void setPrinCiudad(PrinCiudad prinCiudad) {
        this.prinCiudad = prinCiudad;
    }

    public PrinPais getPrinPais() {
        return prinPais;
    }

    public void setPrinPais(PrinPais prinPais) {
        this.prinPais = prinPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPublicacion != null ? codigoPublicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeacPublicacionDga)) {
            return false;
        }
        GeacPublicacionDga other = (GeacPublicacionDga) object;
        if ((this.codigoPublicacion == null && other.codigoPublicacion != null) || (this.codigoPublicacion != null && !this.codigoPublicacion.equals(other.codigoPublicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.publicacionDGA.entities.GeacPublicacionDga[ codigoPublicacion=" + codigoPublicacion + " ]";
    }

    public String getNombrePERSONA() {
        return nombrePERSONA;
    }

    public void setNombrePERSONA(String nombrePERSONA) {
        this.nombrePERSONA = nombrePERSONA;
    }

    public String getNomTipoContrato() {
        return nomTipoContrato;
    }

    public void setNomTipoContrato(String nomTipoContrato) {
        this.nomTipoContrato = nomTipoContrato;
    }

    public String getNomDedicacion() {
        return nomDedicacion;
    }

    public void setNomDedicacion(String nomDedicacion) {
        this.nomDedicacion = nomDedicacion;
    }

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        this.nomArea = nomArea;
    }

}
