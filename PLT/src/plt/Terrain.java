package plt;

import java.io.*;			//added by soonhac @ 02/20/09

public class Terrain {

    private static final int MAP_SIZE = 1024;                        // Size Of Our .RAW Height Map (NEW)
    private static final int STEP_SIZE = 16;                        // Width And Height Of Each Quad (NEW)
    private byte[] heightMap = new byte[MAP_SIZE * MAP_SIZE]; // Holds The Height Map Data (NEW)
    private float scaleValue = .15f;                        // Scale Value For The Terrain (NEW)
    private boolean zoomIn;
    private boolean zoomOut;
    
	
	public Terrain()
	{
		
	}
	
    private void loadRawFile(String strName, byte[] pHeightMap) throws IOException {
        InputStream input = ResourceRetriever.getResourceAsStream(strName);
        readBuffer(input, pHeightMap);
        input.close();

        for (int i = 0; i < pHeightMap.length; i++)
            pHeightMap[i] &= 0xFF;                 //Quick fix
    }
    
    private static void readBuffer(InputStream in, byte[] buffer) throws IOException {
	    int bytesRead = 0;
	    int bytesToRead = buffer.length;
	    while (bytesToRead > 0) {
	        int read = in.read(buffer, bytesRead, bytesToRead);
	        bytesRead += read;
	        bytesToRead -= read;
	    }
	}
}


