package Controller;

import DAO.UsuarioDAO;
import Models.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioMB {
    
    private Usuario usuario;
    private UsuarioDAO dao = new UsuarioDAO();
    private List<Usuario> list;

    
    public UsuarioMB(){
        usuario = new Usuario();
    }

    public void init() {
        list = new ArrayList<Usuario>();
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

    /**
     * @return the list
     */
    public List<Usuario> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Usuario> list) {
        this.list = list;
    }
}
