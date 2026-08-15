package uat.runnables;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import uat.mocks.MockDataUtils;
import uat.models.XwyyOutput;
import uat.response.Response;

public class RunTask implements IRunTask, Runnable {
    private boolean running = false;
    private Thread thread;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        this.running = true;

        if (!running) {
            System.out.println("Task is not running. Exiting run method.");
            return;
        }

        System.out.println("Trigger fired! Running automated job at: " + System.currentTimeMillis());
    
        // Mocking XwyyOutput data processing
        try {
            Thread.sleep(20000); // Simulate a task taking 20 seconds

            XwyyOutput mockOutput = MockDataUtils.createMockXwyyOutput();
            // Process the mock output as needed
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(mockOutput);

            executeTask(mockOutput);
            
            System.out.println("Processed mock XwyyOutput: " + jsonString);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** (non-Javadoc)
     * @see uat.runnables.IRunTask#stop()
     */
    @Override
    public void stop() {
        // TODO Auto-generated method stub
        running = false;
        thread.interrupt();
        System.out.println("Task has been stopped.");
    }

    /** (non-Javadoc)
     * @see uat.runnables.IRunTask#start()
     */
    @Override
    public void start() {
        // TODO Auto-generated method stub
        running = true;
        thread = new Thread(this);
        thread.start();
        System.out.println("Task has been started.");
    }

    public void executeTask(XwyyOutput model){
        // rest to xwyyoutput controller
            // http://localhost:8080/api/xwyyoutput/createOutput
            // You can use RestTemplate or WebClient to send the data to the controller
            // RestTemplate restTemplate = new RestTemplate();
            // restTemplate.postForObject("http://localhost:8080/api/xwyyoutput/createOutput", mockOutput, Void.class);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/xwyyoutput/createOutput";
        try {
            Response response = restTemplate.postForObject(url, model, Response.class);
            if (response != null && response.isSuccess()) {
                System.out.println("Successfully sent mock XwyyOutput to the controller.");
                System.out.println("" + JsonMapper.builder().build().writeValueAsString(response));
            } else {
                System.err.println("Failed to send mock XwyyOutput to the controller. Response: "
                        + JsonMapper.builder().build().writeValueAsString(response));
            }
            
        } catch (Exception e) {
            System.err.println("Error sending mock XwyyOutput to the controller: " + e.getMessage());
        }
    }
}
