package Controller;

import DAO.UsuarioDAO;
import Models.Usuario;
import java.util.List;
import javax.faces.context.FacesContext;

public class UsuarioMB {
    
    private Usuario usuario;
    private UsuarioDAO dao = new UsuarioDAO();
    private List<Usuario> listaUsuario;
    
    public UsuarioMB(){
        usuario = new Usuario();
    }
    
    public Usuario cadastrar() throws Exception{
        usuario = dao.cadastrar(usuario);
        if(usuario != null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("usuarioList.xhtml");  // Redireciona após cadastrar
        }   
        return usuario;
    }
    
    public List<Usuario> listar(){
       return listaUsuario = dao.listar();
    }
            
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @return the listaUsuario
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
}
