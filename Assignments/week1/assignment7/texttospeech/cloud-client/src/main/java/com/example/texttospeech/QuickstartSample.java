/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.example.texttospeech;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QuickstartSample {

  public static void main(String... args) throws Exception {

    String plainText = new String(
        Files.readAllBytes(Paths.get("resources/frog.txt"))
    );

    String ssmlText = new String(
        Files.readAllBytes(Paths.get("resources/frog.ssml"))
    );

    try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {

      VoiceSelectionParams voice =
          VoiceSelectionParams.newBuilder()
              .setLanguageCode("en-US")
              .setSsmlGender(SsmlVoiceGender.NEUTRAL)
              .build();

      AudioConfig audioConfig =
          AudioConfig.newBuilder()
              .setAudioEncoding(AudioEncoding.MP3)
              .build();

      SynthesisInput plainInput =
          SynthesisInput.newBuilder()
              .setText(plainText)
              .build();

      SynthesizeSpeechResponse plainResponse =
          textToSpeechClient.synthesizeSpeech(plainInput, voice, audioConfig);

      ByteString plainAudioContents = plainResponse.getAudioContent();

      try (OutputStream out = new FileOutputStream("frog_plain_output.mp3")) {
        out.write(plainAudioContents.toByteArray());
        System.out.println("Audio content written to file \"frog_plain_output.mp3\"");
      }

      SynthesisInput ssmlInput =
          SynthesisInput.newBuilder()
              .setSsml(ssmlText)
              .build();

      SynthesizeSpeechResponse ssmlResponse =
          textToSpeechClient.synthesizeSpeech(ssmlInput, voice, audioConfig);

      ByteString ssmlAudioContents = ssmlResponse.getAudioContent();

      try (OutputStream out = new FileOutputStream("frog_ssml_output.mp3")) {
        out.write(ssmlAudioContents.toByteArray());
        System.out.println("Audio content written to file \"frog_ssml_output.mp3\"");
      }
    }
  }
}