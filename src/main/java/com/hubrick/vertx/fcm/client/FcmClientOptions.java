/**
 * Copyright (C) 2017 Etaia AS (oss@hubrick.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hubrick.vertx.fcm.client;

import com.hubrick.vertx.rest.RequestCacheOptions;
import com.hubrick.vertx.rest.RestClientOptions;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JdkSSLEngineOptions;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.SSLEngineOptions;
import io.vertx.core.net.TrustOptions;

import java.util.List;

/**
 * @author marcus
 * @since 1.0.0
 */
public class FcmClientOptions extends RestClientOptions {

    private String fcmServerKey;
    private Long globalTimeoutMs = 10000L;
    private String hostnameOverride;

    public FcmClientOptions() {
        super();
    }

    public FcmClientOptions(final FcmClientOptions other) {
        super(other);

        setFcmServerKey(other.getFcmServerKey());
        setGlobalTimeoutMs(other.getGlobalTimeoutMs());
        setHostnameOverride(other.getHostnameOverride());
    }

    public FcmClientOptions(final HttpClientOptions other) {
        super(other);
    }

    public FcmClientOptions(final JsonObject json) {
        super(json);

        setFcmServerKey(json.getString("fcmServerKey"));
        setGlobalTimeoutMs(json.getLong("globalTimeoutMs"));
        setHostnameOverride(json.getString("hostnameOverride"));
    }

    public String getFcmServerKey() {
        return fcmServerKey;
    }

    public FcmClientOptions setFcmServerKey(final String fcmServerKey) {
        this.fcmServerKey = fcmServerKey;
        return this;
    }

    public String getHostnameOverride() {
        return hostnameOverride;
    }

    public FcmClientOptions setHostnameOverride(final String hostnameOverride) {
        this.hostnameOverride = hostnameOverride;
        return this;
    }

    public Long getGlobalTimeoutMs() {
        return globalTimeoutMs;
    }

    public FcmClientOptions setGlobalTimeoutMs(final Long globalTimeoutMs) {
        this.globalTimeoutMs = globalTimeoutMs;
        return this;
    }

    @Override
    public FcmClientOptions setSendBufferSize(final int sendBufferSize) {
        super.setSendBufferSize(sendBufferSize);
        return this;
    }

    @Override
    public FcmClientOptions setReceiveBufferSize(final int receiveBufferSize) {
        super.setReceiveBufferSize(receiveBufferSize);
        return this;
    }

    @Override
    public FcmClientOptions setReuseAddress(final boolean reuseAddress) {
        super.setReuseAddress(reuseAddress);
        return this;
    }

    @Override
    public FcmClientOptions setTrafficClass(final int trafficClass) {
        super.setTrafficClass(trafficClass);
        return this;
    }

    @Override
    public FcmClientOptions setTcpNoDelay(final boolean tcpNoDelay) {
        super.setTcpNoDelay(tcpNoDelay);
        return this;
    }

    @Override
    public FcmClientOptions setTcpKeepAlive(final boolean tcpKeepAlive) {
        super.setTcpKeepAlive(tcpKeepAlive);
        return this;
    }

    @Override
    public FcmClientOptions setSoLinger(final int soLinger) {
        super.setSoLinger(soLinger);
        return this;
    }

    @Override
    public FcmClientOptions setUsePooledBuffers(final boolean usePooledBuffers) {
        super.setUsePooledBuffers(usePooledBuffers);
        return this;
    }

    @Override
    public FcmClientOptions setIdleTimeout(final int idleTimeout) {
        super.setIdleTimeout(idleTimeout);
        return this;
    }

    @Override
    public FcmClientOptions setSsl(final boolean ssl) {
        super.setSsl(ssl);
        return this;
    }

    @Override
    public FcmClientOptions setKeyCertOptions(final KeyCertOptions options) {
        super.setKeyCertOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions setKeyStoreOptions(final JksOptions options) {
        super.setKeyStoreOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions setPfxKeyCertOptions(final PfxOptions options) {
        super.setPfxKeyCertOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions setTrustOptions(final TrustOptions options) {
        super.setTrustOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions setPemKeyCertOptions(final PemKeyCertOptions options) {
        super.setPemKeyCertOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions setTrustStoreOptions(final JksOptions options) {
        super.setTrustStoreOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions setPfxTrustOptions(final PfxOptions options) {
        super.setPfxTrustOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions setPemTrustOptions(final PemTrustOptions options) {
        super.setPemTrustOptions(options);
        return this;
    }

    @Override
    public FcmClientOptions addEnabledCipherSuite(final String suite) {
        super.addEnabledCipherSuite(suite);
        return this;
    }

    @Override
    public FcmClientOptions addEnabledSecureTransportProtocol(final String protocol) {
        super.addEnabledSecureTransportProtocol(protocol);
        return this;
    }

    @Override
    public FcmClientOptions addCrlPath(final String crlPath) throws NullPointerException {
        super.addCrlPath(crlPath);
        return this;
    }

    @Override
    public FcmClientOptions addCrlValue(final Buffer crlValue) throws NullPointerException {
        super.addCrlValue(crlValue);
        return this;
    }

    @Override
    public FcmClientOptions setConnectTimeout(final int connectTimeout) {
        super.setConnectTimeout(connectTimeout);
        return this;
    }

    @Override
    public FcmClientOptions setTrustAll(final boolean trustAll) {
        super.setTrustAll(trustAll);
        return this;
    }

    @Override
    public FcmClientOptions setHttp2MultiplexingLimit(final int limit) {
        super.setHttp2MultiplexingLimit(limit);
        return this;
    }

    @Override
    public FcmClientOptions setMaxPoolSize(final int maxPoolSize) {
        super.setMaxPoolSize(maxPoolSize);
        return this;
    }

    @Override
    public FcmClientOptions setHttp2MaxPoolSize(final int max) {
        super.setHttp2MaxPoolSize(max);
        return this;
    }

    @Override
    public FcmClientOptions setHttp2ConnectionWindowSize(final int http2ConnectionWindowSize) {
        super.setHttp2ConnectionWindowSize(http2ConnectionWindowSize);
        return this;
    }

    @Override
    public FcmClientOptions setKeepAlive(final boolean keepAlive) {
        super.setKeepAlive(keepAlive);
        return this;
    }

    @Override
    public FcmClientOptions setPipelining(final boolean pipelining) {
        super.setPipelining(pipelining);
        return this;
    }

    @Override
    public FcmClientOptions setPipeliningLimit(final int limit) {
        super.setPipeliningLimit(limit);
        return this;
    }

    @Override
    public FcmClientOptions setVerifyHost(final boolean verifyHost) {
        super.setVerifyHost(verifyHost);
        return this;
    }

    @Override
    public FcmClientOptions setTryUseCompression(final boolean tryUseCompression) {
        super.setTryUseCompression(tryUseCompression);
        return this;
    }

    @Override
    public FcmClientOptions setMaxWebsocketFrameSize(final int maxWebsocketFrameSize) {
        super.setMaxWebsocketFrameSize(maxWebsocketFrameSize);
        return this;
    }

    @Override
    public FcmClientOptions setDefaultHost(final String defaultHost) {
        super.setDefaultHost(defaultHost);
        return this;
    }

    @Override
    public FcmClientOptions setDefaultPort(final int defaultPort) {
        super.setDefaultPort(defaultPort);
        return this;
    }

    @Override
    public FcmClientOptions setProtocolVersion(final HttpVersion protocolVersion) {
        super.setProtocolVersion(protocolVersion);
        return this;
    }

    @Override
    public FcmClientOptions setMaxChunkSize(final int maxChunkSize) {
        super.setMaxChunkSize(maxChunkSize);
        return this;
    }

    @Override
    public FcmClientOptions setMaxWaitQueueSize(final int maxWaitQueueSize) {
        super.setMaxWaitQueueSize(maxWaitQueueSize);
        return this;
    }

    @Override
    public FcmClientOptions setInitialSettings(final Http2Settings settings) {
        super.setInitialSettings(settings);
        return this;
    }

    @Override
    public FcmClientOptions setSslEngineOptions(final SSLEngineOptions sslEngineOptions) {
        super.setSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override
    public FcmClientOptions setJdkSslEngineOptions(final JdkSSLEngineOptions sslEngineOptions) {
        super.setJdkSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override
    public FcmClientOptions setOpenSslEngineOptions(final OpenSSLEngineOptions sslEngineOptions) {
        super.setOpenSslEngineOptions(sslEngineOptions);
        return this;
    }

    @Override
    public FcmClientOptions setAlpnVersions(final List<HttpVersion> alpnVersions) {
        super.setAlpnVersions(alpnVersions);
        return this;
    }

    @Override
    public FcmClientOptions setHttp2ClearTextUpgrade(final boolean value) {
        super.setHttp2ClearTextUpgrade(value);
        return this;
    }

    @Override
    public FcmClientOptions setMetricsName(final String metricsName) {
        super.setMetricsName(metricsName);
        return this;
    }

    @Override
    public FcmClientOptions setProxyOptions(final ProxyOptions proxyOptions) {
        super.setProxyOptions(proxyOptions);
        return this;
    }

    @Override
    public FcmClientOptions setLogActivity(final boolean logEnabled) {
        super.setLogActivity(logEnabled);
        return this;
    }

    @Override
    public FcmClientOptions setGlobalRequestTimeout(final long requestTimeoutInMillis) {
        super.setGlobalRequestTimeout(requestTimeoutInMillis);
        return this;
    }

    @Override
    public FcmClientOptions putGlobalHeaders(final MultiMap headers) {
        super.putGlobalHeaders(headers);
        return this;
    }

    @Override
    public FcmClientOptions putGlobalHeader(final String name, final String value) {
        super.putGlobalHeader(name, value);
        return this;
    }

    @Override
    public FcmClientOptions setGlobalRequestCacheOptions(final RequestCacheOptions requestCacheOptions) {
        super.setGlobalRequestCacheOptions(requestCacheOptions);
        return this;
    }

    @Override
    public FcmClientOptions setSendUnmaskedFrames(final boolean sendUnmaskedFrames) {
        super.setSendUnmaskedFrames(sendUnmaskedFrames);
        return this;
    }

    @Override
    public FcmClientOptions setMaxWebsocketMessageSize(final int maxWebsocketMessageSize) {
        super.setMaxWebsocketMessageSize(maxWebsocketMessageSize);
        return this;
    }

    @Override
    public FcmClientOptions setMaxInitialLineLength(final int maxInitialLineLength) {
        super.setMaxInitialLineLength(maxInitialLineLength);
        return this;
    }

    @Override
    public FcmClientOptions setMaxHeaderSize(final int maxHeaderSize) {
        super.setMaxHeaderSize(maxHeaderSize);
        return this;
    }

    @Override
    public FcmClientOptions setUseAlpn(final boolean useAlpn) {
        super.setUseAlpn(useAlpn);
        return this;
    }

    @Override
    public FcmClientOptions setMaxRedirects(final int maxRedirects) {
        super.setMaxRedirects(maxRedirects);
        return this;
    }

    @Override
    public FcmClientOptions setForceSni(final boolean forceSni) {
        super.setForceSni(forceSni);
        return this;
    }

    @Override
    public FcmClientOptions setLocalAddress(final String localAddress) {
        super.setLocalAddress(localAddress);
        return this;
    }

    @Override
    public FcmClientOptions setDecoderInitialBufferSize(final int decoderInitialBufferSize) {
        super.setDecoderInitialBufferSize(decoderInitialBufferSize);
        return this;
    }
}
