import java.io.File;
import java.io.FileFilter;
import java.sql.Connection;
import javax.swing.JOptionPane;
  
public class Monitor implements Runnable {  
  
    private Thread threadMonitor;  
    private String dir;  
  
    public static void main(String[] args) {  
        new Monitor().init();
    }  
  
    private void init() {  
    	// chamada classe para conexao mysql
    	Connection conn = new FabricaDeConexoes().getConnection();

    	JOptionPane.showMessageDialog(null,"Conexão está OK","Mensagem",JOptionPane.INFORMATION_MESSAGE);
        
        // diretorio onde vai ser feito a verificacao  
        setDir("C:\\ABCFARMA");  
          
        this.threadMonitor = new Thread(this);  
        this.threadMonitor.start();  
    }
    
    private void verificaPasta(String dir) {  
        File diretorio = new File(dir);  
          
        // lista somente arquivos XML  
        File arquivos[] = diretorio.listFiles(new FileFilter() {  
            public boolean accept(File pathname) {  
                return pathname.getName().toLowerCase().endsWith(".txt");  
            }  
        });  
  
        for (int i = 0; i < arquivos.length; i++) {  
            // se encontrar algum arquivo XML na pasta   
            // chama metodo  
            File file = arquivos[i];
            newFile(file.getName());  
        }  
    }  
  
    public void newFile(String file) {  
        System.out.println("evento -> novo arquivo encontrado!! " + file);  
    }  
  
    @Override  
    public void run() {  
        Thread currentThread = Thread.currentThread();  
        // thread que fica verificando a pasta  

        while (this.threadMonitor == currentThread) {  
              
            verificaPasta(getDir()); 
              
            try {  
                Thread.sleep(1000 * 10); // 10 segundos  
            } catch (InterruptedException e) {}  
        }  
    }  
  
    public String getDir() {  
        return dir;  
    }  
  
    public void setDir(String dir) {  
        this.dir = dir;  
    }  
}  