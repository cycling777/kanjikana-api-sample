/*
 * MIT License
 *
 * Copyright (c) 2025 デジタル庁
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jp.go.digital.kanjikana.samples;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


public class App 
{
    public static void main( String[] args )throws ArgumentParserException, IOException
    {
        ArgumentParser parser = ArgumentParsers.newFor("kanjikana").build().defaultHelp(true).description("氏名突合");

        parser.addArgument("--apikey").help("https://kktg.digital.go.jp で取得した API キー");
        parser.addArgument("--kanji").setDefault("日本［東京］　花子").help("漢字氏名、［］内は旧氏");
        parser.addArgument("--kana").setDefault("トウキョウ　ハナコ").help("カナ氏名");
        parser.addArgument("--method").setDefault("simple").choices(Arrays.asList("simple", "detail")).help("simpleは簡易モデル、detailは詳細モデル");

        Namespace ns = parser.parseArgs(args);

        String apikey = ns.getString("apikey");
        String kanji = ns.getString("kanji");
        String kana = ns.getString("kana");
        String method = ns.getString("method");

        URL url = new URL("https://api.kktg.digital.go.jp/v1/"+method+"&kanji="+kanji+"&kana="+kana+"&key="+apikey);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Response response = mapper.readValue(responseStream, Response.class);

        System.out.println(response.result.status);

    }
}
