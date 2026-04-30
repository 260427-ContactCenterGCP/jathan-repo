from google.cloud.speech_v2 import SpeechClient
from google.cloud.speech_v2.types import cloud_speech

PROJECT_ID = "project-86e5d1df-f58e-4fb6-b2c"
LOCATION = "us-central1"

client = SpeechClient(
    client_options={"api_endpoint": f"{LOCATION}-speech.googleapis.com"}
)

audio_files = [
    "resources/betcatchknife(online-audio-converter.com).mp3",
    "resources/steponthescale(online-audio-converter.com).mp3"
]

for audio_file in audio_files:
    with open(audio_file, "rb") as f:
        audio_content = f.read()

    config = cloud_speech.RecognitionConfig(
        auto_decoding_config=cloud_speech.AutoDetectDecodingConfig(),
        language_codes=["en-US"],
        model="short",
    )

    request = cloud_speech.RecognizeRequest(
        recognizer=f"projects/{PROJECT_ID}/locations/{LOCATION}/recognizers/_",
        config=config,
        content=audio_content,
    )

    response = client.recognize(request=request)

    print(f"Audio File: {audio_file}")

    if len(response.results) == 0:
        print("Transcript: No transcript detected")
        print("Confidence: N/A")
    else:
        for result in response.results:
            print(f"Transcript: {result.alternatives[0].transcript}")
            print(f"Confidence: {result.alternatives[0].confidence}")

    print()