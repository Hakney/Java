package Controller;

import DAO.UsuarioDAO;
import Models.Usuario;
import java.io.IOException;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class UsuarioMB {
    
    private Usuario usuario;
    private UsuarioDAO dao = new UsuarioDAO();
    private List<Usuario> listaUsuario;
    
    public UsuarioMB(){
        if (usuario == null) {
            ExternalContext ctx = FacesContext
                                      .getCurrentInstance()
                                      .getExternalContext();
 
            String idParam = ctx.getRequestParameterMap().get("id");
 
            if (idParam != null && !idParam.equals("")) {
                try {
                    this.usuario = dao.buscar(Integer.parseInt(idParam));
                } catch(NumberFormatException e) {
                    // log
                }
            } 
 
            if (this.usuario == null) {
                this.usuario = new Usuario();
            }
        }
    }
    
    public Usuario cadastrar() throws Exception{
        usuario = dao.cadastrar(usuario);
        if(usuario != null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("usuarioList.xhtml");  // Redireciona após cadastrar
        }   
        return usuario;
    }
    
    public void excluir(Integer id){
        dao.excluir(id);
    }
    
    public List<Usuario> listar(){
       return listaUsuario = dao.listar();
    }
    
    public Usuario buscar(Usuario usuario) throws IOException{    
        usuario = dao.buscar(usuario.getId());         
        return usuario;
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
