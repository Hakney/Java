package Controller;

import DAO.UsuarioDAO;
import Models.Usuario;
import javax.faces.context.FacesContext;

public class UsuarioMB {
    
    private Usuario usuario;
    private UsuarioDAO dao = new UsuarioDAO();

    public UsuarioMB(){
        usuario = new Usuario();
    }
    
    public Usuario cadastrar() throws Exception{
        usuario = dao.cadastrar(usuario);
        if(usuario != null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("pag-sucess.xhtml");  // Redireciona após cadastrar
        }   
        return usuario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void msgbox(String dont_touch_that) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
