import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Monitor
{
	public static void main(String args[]){

		Path path = Paths.get(setDiretorio("c:\\dados"));
		WatchService ws = null;
		//Interface ui = new Interface();

		try {
			ws = FileSystems.getDefault().newWatchService();
			path.register(ws, StandardWatchEventKinds.ENTRY_CREATE);
			path.register(ws, StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		while (true) {
			WatchKey key = null;

			try {
				key = ws.take();
				for (WatchEvent<?> event : key.pollEvents()) {
					// Kind<?> kind = event.kind();
//					ui.setTexto("Novo arquivo adicionado => "+event.context().toString());
				System.out.println("Novo arquivo inserido => "
				+ event.context().toString());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			boolean reset = key.reset();
			if (!reset)
				break;
		} // Fim while
	}

	public static String setDiretorio(String dir) {
		return dir;
	}


} // Fim Classe Main